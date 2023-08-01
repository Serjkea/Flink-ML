package org.example;

import org.apache.flink.ml.linalg.Vectors;
import org.apache.flink.types.Row;

import java.util.Arrays;
import java.util.List;

public class TrainData {

    public static List<Row> getTrainData = Arrays.asList(
            Row.of(Vectors.dense(0.0, 0.0, 0.0, 20.0, 0.0), 0.0),
            Row.of(Vectors.dense(20.0, 0.0, 0.0, 20.0, 0.0), 0.0),
            Row.of(Vectors.dense(40.0, 0.0, 0.0, 20.0, 0.0), 0.0),
            Row.of(Vectors.dense(60.0, 0.0, 0.0, 20.0, 0.0), 0.0),
            Row.of(Vectors.dense(80.0, 0.0, 0.0, 20.0, 0.0), 0.0),
            Row.of(Vectors.dense(95.0, 0.0, 0.0, 20.0, 0.0), 1.0),
            Row.of(Vectors.dense(105.0, 0.0, 0.0, 20.0, 0.0), 2.0),
            Row.of(Vectors.dense(120.0, 0.0, 0.0, 20.0, 0.0), 3.0),
            Row.of(Vectors.dense(150.0, 0.0, 0.0, 20.0, 0.0), 4.0),
            Row.of(Vectors.dense(200.0, 0.0, 0.0, 20.0, 0.0), 5.0),

            Row.of(Vectors.dense(0.0, 0.0, 0.0, 20.0, 0.0), 0.0),
            Row.of(Vectors.dense(20.0, 0.0, 0.0, 20.0, 0.0), 0.0),
            Row.of(Vectors.dense(40.0, 0.0, 0.0, 20.0, 0.0), 0.0),
            Row.of(Vectors.dense(60.0, 0.0, 0.0, 20.0, 0.0), 1.0),
            Row.of(Vectors.dense(80.0, 0.0, 0.0, 20.0, 0.0), 2.0),
            Row.of(Vectors.dense(95.0, 0.0, 0.0, 20.0, 0.0), 3.0),
            Row.of(Vectors.dense(105.0, 0.0, 0.0, 20.0, 0.0), 4.0),
            Row.of(Vectors.dense(120.0, 0.0, 0.0, 20.0, 0.0), 6.0),
            Row.of(Vectors.dense(150.0, 120.0, 0.0, 20.0, 0.0), 8.0),
            Row.of(Vectors.dense(200.0, 150.0, 0.0, 20.0, 0.0), 10.0),

            Row.of(Vectors.dense(0.0, 0.0, 0.0, 0.0, 0.0), 0.0),
            Row.of(Vectors.dense(20.0, 0.0, 0.0, 0.0, 0.0), 0.0),
            Row.of(Vectors.dense(40.0, 0.0, 0.0, 0.0, 0.0), 0.0),
            Row.of(Vectors.dense(60.0, 0.0, 0.0, 0.0, 0.0), 0.0),
            Row.of(Vectors.dense(80.0, 0.0, 0.0, 0.0, 0.0), 1.0),
            Row.of(Vectors.dense(95.0, 0.0, 0.0, 0.0, 0.0), 2.0),
            Row.of(Vectors.dense(105.0, 0.0, 0.0, 0.0, 0.0), 3.0),
            Row.of(Vectors.dense(120.0, 0.0, 0.0, 0.0, 0.0), 4.0),
            Row.of(Vectors.dense(150.0, 120.0, 0.0, 0.0, 0.0), 6.0),
            Row.of(Vectors.dense(200.0, 150.0, 0.0, 0.0, 0.0), 8.0),

            Row.of(Vectors.dense(0.0, 0.0, 800.0, 20.0, 1000.0), 0.0),
            Row.of(Vectors.dense(20.0, 15.0, 800.0, 20.0, 1000.0), 0.0),
            Row.of(Vectors.dense(40.0, 30.0, 800.0, 20.0, 1000.0), 0.0),
            Row.of(Vectors.dense(60.0, 40.0, 800.0, 20.0, 1000.0), 1.0),
            Row.of(Vectors.dense(80.0, 60.0, 800.0, 20.0, 1000.0), 2.0),
            Row.of(Vectors.dense(95.0, 70.0, 800.0, 20.0, 1000.0), 3.0),
            Row.of(Vectors.dense(105.0, 85.0, 800.0, 20.0, 1000.0), 5.0),
            Row.of(Vectors.dense(120.0, 100.0, 800.0, 20.0, 1000.0), 8.0),
            Row.of(Vectors.dense(150.0, 120.0, 800.0, 20.0, 1000.0), 10.0),
            Row.of(Vectors.dense(200.0, 150.0, 800.0, 20.0, 1000.0), 10.0),

            Row.of(Vectors.dense(0.0, 0.0, 7000.0, 20.0, 1000.0), 9.0),
            Row.of(Vectors.dense(20.0, 15.0, 7000.0, 20.0, 1000.0), 8.0),
            Row.of(Vectors.dense(40.0, 30.0, 7000.0, 20.0, 1000.0), 7.0),
            Row.of(Vectors.dense(60.0, 40.0, 7000.0, 20.0, 1000.0), 6.0),
            Row.of(Vectors.dense(80.0, 60.0, 7000.0, 20.0, 1000.0), 5.0),
            Row.of(Vectors.dense(95.0, 70.0, 7000.0, 20.0, 1000.0), 4.0),
            Row.of(Vectors.dense(105.0, 85.0, 7000.0, 20.0, 1000.0), 5.0),
            Row.of(Vectors.dense(120.0, 100.0, 7000.0, 20.0, 1000.0), 8.0),
            Row.of(Vectors.dense(150.0, 120.0, 7000.0, 20.0, 1000.0), 9.0),
            Row.of(Vectors.dense(200.0, 150.0, 7000.0, 20.0, 1000.0), 10.0),

            Row.of(Vectors.dense(0.0, 0.0, 3000.0, 20.0, 1000.0), 3.0),
            Row.of(Vectors.dense(20.0, 15.0, 3000.0, 20.0, 1000.0), 2.0),
            Row.of(Vectors.dense(40.0, 30.0, 3000.0, 20.0, 1000.0), 1.0),
            Row.of(Vectors.dense(60.0, 40.0, 3000.0, 20.0, 1000.0), 1.0),
            Row.of(Vectors.dense(80.0, 60.0, 3000.0, 20.0, 1000.0), 1.0),
            Row.of(Vectors.dense(95.0, 70.0, 3000.0, 20.0, 1000.0), 1.0),
            Row.of(Vectors.dense(105.0, 85.0, 3000.0, 20.0, 1000.0), 3.0),
            Row.of(Vectors.dense(120.0, 100.0, 3000.0, 20.0, 1000.0), 5.0),
            Row.of(Vectors.dense(150.0, 120.0, 3000.0, 20.0, 1000.0), 8.0),
            Row.of(Vectors.dense(200.0, 150.0, 3000.0, 20.0, 1000.0), 10.0));

}
