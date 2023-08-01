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
import org.apache.flink.util.CloseableIterator;

import static org.apache.flink.ml.Functions.arrayToVector;
import static org.apache.flink.table.api.Expressions.$;

public class RealTimePrediction {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.STREAMING);

        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        TableDescriptor tableDescriptor = TableDescriptor
                .forConnector("kafka")
                .schema(
                        Schema.newBuilder()
                                .column("oil_temp", DataTypes.DOUBLE())
                                .column("water_temp", DataTypes.DOUBLE())
                                .column("engine_speed", DataTypes.DOUBLE())
                                .column("air_temp", DataTypes.DOUBLE())
                                .column("fan_speed", DataTypes.DOUBLE())
                                .column("label", DataTypes.DOUBLE())
                                .build()
                )
                .format("csv")
                .option("topic", "input-topic")
                .option("properties.bootstrap.servers", "localhost:29092")
                .option("scan.startup.mode", "earliest-offset")
                .build();

        tableEnv.createTable("features", tableDescriptor);

        DataStream<Row> trainStream = env.fromCollection(TrainData.getTrainData);

        Table trainTable = tableEnv.fromDataStream(trainStream).as("features", "label");

        Knn knn = new Knn().setK(4);

        KnnModel knnModel = knn.fit(trainTable);

        Table predictTable = tableEnv
                .sqlQuery("SELECT ARRAY[oil_temp, water_temp, engine_speed, air_temp, fan_speed] AS feature, label FROM features")
                .select(arrayToVector($("feature")).as("features"), $("label"));

        Table outputTable = knnModel.transform(predictTable)[0];

        for (CloseableIterator<Row> it = outputTable.execute().collect(); it.hasNext(); ) {
            Row row = it.next();
            DenseVector features = (DenseVector) row.getField(knn.getFeaturesCol());
            double predictionResult = (Double) row.getField(knn.getPredictionCol());
            System.out.printf(
                    "Features: %-15s \tPrediction Result: %s\n",
                    features, predictionResult);
        }
    }
}

