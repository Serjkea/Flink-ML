package org.example;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.ml.classification.knn.Knn;
import org.apache.flink.ml.classification.knn.KnnModel;
import org.apache.flink.ml.linalg.DenseVector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.Schema;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableDescriptor;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Optional;
import java.util.Properties;

import static org.apache.flink.ml.Functions.arrayToVector;
import static org.apache.flink.table.api.Expressions.$;

public class RealTimePrediction {

    private static Optional<String> maybeCreateAlertMessage(double predictionResult) {
        if (predictionResult >= 5.0 && predictionResult < 7.0) {
            return Optional.of("WARNING. The system load rating has exceeded 5.0 and is equal to " + predictionResult);
        } else if (predictionResult >= 7.0) {
            return Optional.of("CRITICAL. The system load rating has exceeded 7.0 and is equal to " + predictionResult);
        } else return Optional.empty();
    }

    public static void main(String[] args) {

        String kafkaHost = "localhost:9092";

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.STREAMING);

        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        TableDescriptor tableDescriptor = TableDescriptor
                .forConnector("kafka")
                .schema(
                        Schema.newBuilder()
                                .column("f1", DataTypes.DOUBLE())
                                .column("f2", DataTypes.DOUBLE())
                                .column("f3", DataTypes.DOUBLE())
                                .column("f4", DataTypes.DOUBLE())
                                .column("f5", DataTypes.DOUBLE())
                                .column("label", DataTypes.DOUBLE())
                                .build()
                )
                .format("csv")
                .option("topic", "input-topic")
                .option("properties.bootstrap.servers", kafkaHost)
                .option("scan.startup.mode", "latest-offset")
                .build();

        tableEnv.createTable("features_table", tableDescriptor);

        DataStream<Row> trainStream = env.fromCollection(TrainData.getTrainData);

        Table trainTable = tableEnv.fromDataStream(trainStream).as("features", "label");

        Knn knn = new Knn().setK(4);

        KnnModel knnModel = knn.fit(trainTable);

        Table predictTable = tableEnv
                .sqlQuery("SELECT ARRAY[f1, f2, f3, f4, f5] AS features, label FROM features_table")
                .select(arrayToVector($("features")).as("features"), $("label"));

        Table outputTable = knnModel.transform(predictTable)[0];

        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.ACKS_CONFIG, "all");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        outputTable.execute().collect().forEachRemaining(row -> {
            DenseVector features = (DenseVector) row.getField(knn.getFeaturesCol());
            double predictionResult = (Double) row.getField(knn.getPredictionCol());
            System.out.printf(
                    "Features: %-15s \tPrediction Result: %s\n",
                    features, predictionResult);
            maybeCreateAlertMessage(predictionResult).ifPresent(msg -> {
                ProducerRecord<String, String> message = new ProducerRecord<>("alert-topic", "message-key", msg);
                producer.send(message);
            });
        });

        producer.close();
    }
}

