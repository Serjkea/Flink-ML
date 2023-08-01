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
                .option("properties.bootstrap.servers", "localhost:9092")
                .option("scan.startup.mode", "earliest-offset")
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

