package org.example;

import org.apache.flink.ml.linalg.Vectors;
import org.apache.flink.types.Row;

import java.util.Arrays;
import java.util.List;

public class TrainData {

    public static List<Row> getTrainData = Arrays.asList(
            Row.of(Vectors.dense(0.0, 0.0, 0.0, 2.0, 0.0), 0.0),
            Row.of(Vectors.dense(1.0, 0.0, 0.0, 2.0, 0.0), 0.0),
            Row.of(Vectors.dense(1.0, 0.0, 0.0, 2.0, 0.0), 0.0),
            Row.of(Vectors.dense(1.0, 0.0, 0.0, 2.0, 0.0), 0.0),
            Row.of(Vectors.dense(2.0, 0.0, 0.0, 2.0, 0.0), 0.0),
            Row.of(Vectors.dense(4.0, 0.0, 0.0, 2.0, 0.0), 1.0),
            Row.of(Vectors.dense(6.0, 0.0, 0.0, 2.0, 0.0), 2.0),
            Row.of(Vectors.dense(7.0, 0.0, 0.0, 2.0, 0.0), 3.0),
            Row.of(Vectors.dense(8.0, 0.0, 0.0, 2.0, 0.0), 4.0),
            Row.of(Vectors.dense(9.0, 0.0, 0.0, 2.0, 0.0), 5.0),

            Row.of(Vectors.dense(0.0, 0.0, 0.0, 2.0, 0.0), 0.0),
            Row.of(Vectors.dense(1.0, 0.0, 0.0, 2.0, 0.0), 0.0),
            Row.of(Vectors.dense(1.0, 0.0, 0.0, 2.0, 0.0), 0.0),
            Row.of(Vectors.dense(1.0, 0.0, 0.0, 2.0, 0.0), 1.0),
            Row.of(Vectors.dense(2.0, 0.0, 0.0, 2.0, 0.0), 2.0),
            Row.of(Vectors.dense(4.0, 0.0, 0.0, 2.0, 0.0), 3.0),
            Row.of(Vectors.dense(6.0, 0.0, 0.0, 2.0, 0.0), 4.0),
            Row.of(Vectors.dense(7.0, 0.0, 0.0, 2.0, 0.0), 6.0),
            Row.of(Vectors.dense(8.0, 5.0, 0.0, 2.0, 0.0), 8.0),
            Row.of(Vectors.dense(9.0, 8.0, 0.0, 2.0, 0.0), 10.0),

            Row.of(Vectors.dense(0.0, 0.0, 0.0, 0.0, 0.0), 0.0),
            Row.of(Vectors.dense(1.0, 0.0, 0.0, 0.0, 0.0), 0.0),
            Row.of(Vectors.dense(1.0, 0.0, 0.0, 0.0, 0.0), 0.0),
            Row.of(Vectors.dense(1.0, 0.0, 0.0, 0.0, 0.0), 0.0),
            Row.of(Vectors.dense(2.0, 0.0, 0.0, 0.0, 0.0), 1.0),
            Row.of(Vectors.dense(4.0, 0.0, 0.0, 0.0, 0.0), 2.0),
            Row.of(Vectors.dense(6.0, 0.0, 0.0, 0.0, 0.0), 3.0),
            Row.of(Vectors.dense(7.0, 0.0, 0.0, 0.0, 0.0), 4.0),
            Row.of(Vectors.dense(8.0, 5.0, 0.0, 0.0, 0.0), 6.0),
            Row.of(Vectors.dense(9.0, 8.0, 0.0, 0.0, 0.0), 8.0),

            Row.of(Vectors.dense(0.0, 0.0, 2.0, 2.0, 1.0), 0.0),
            Row.of(Vectors.dense(1.0, 0.0, 2.0, 2.0, 1.0), 0.0),
            Row.of(Vectors.dense(1.0, 0.0, 2.0, 2.0, 1.0), 0.0),
            Row.of(Vectors.dense(1.0, 0.0, 2.0, 2.0, 1.0), 1.0),
            Row.of(Vectors.dense(2.0, 0.0, 2.0, 2.0, 1.0), 2.0),
            Row.of(Vectors.dense(4.0, 0.0, 2.0, 2.0, 1.0), 3.0),
            Row.of(Vectors.dense(6.0, 0.0, 2.0, 2.0, 1.0), 5.0),
            Row.of(Vectors.dense(7.0, 0.0, 2.0, 2.0, 1.0), 8.0),
            Row.of(Vectors.dense(8.0, 5.0, 2.0, 2.0, 1.0), 10.0),
            Row.of(Vectors.dense(9.0, 8.0, 2.0, 2.0, 1.0), 10.0),

            Row.of(Vectors.dense(0.0, 0.0, 8.0, 2.0, 1.0), 9.0),
            Row.of(Vectors.dense(1.0, 1.0, 8.0, 2.0, 1.0), 7.0),
            Row.of(Vectors.dense(1.0, 1.0, 8.0, 2.0, 1.0), 6.0),
            Row.of(Vectors.dense(1.0, 2.0, 8.0, 2.0, 1.0), 5.0),
            Row.of(Vectors.dense(2.0, 2.0, 8.0, 2.0, 1.0), 4.0),
            Row.of(Vectors.dense(4.0, 3.0, 8.0, 2.0, 1.0), 5.0),
            Row.of(Vectors.dense(6.0, 4.0, 8.0, 2.0, 1.0), 5.0),
            Row.of(Vectors.dense(7.0, 6.0, 8.0, 2.0, 1.0), 8.0),
            Row.of(Vectors.dense(8.0, 8.0, 8.0, 2.0, 1.0), 9.0),
            Row.of(Vectors.dense(9.0, 9.0, 8.0, 2.0, 1.0), 10.0),

            Row.of(Vectors.dense(0.0, 0.0, 4.0, 2.0, 1.0), 3.0),
            Row.of(Vectors.dense(1.0, 1.0, 4.0, 2.0, 1.0), 2.0),
            Row.of(Vectors.dense(1.0, 1.0, 4.0, 2.0, 1.0), 1.0),
            Row.of(Vectors.dense(1.0, 2.0, 4.0, 2.0, 1.0), 1.0),
            Row.of(Vectors.dense(2.0, 2.0, 4.0, 2.0, 1.0), 1.0),
            Row.of(Vectors.dense(4.0, 3.0, 4.0, 2.0, 1.0), 1.0),
            Row.of(Vectors.dense(6.0, 4.0, 4.0, 2.0, 1.0), 3.0),
            Row.of(Vectors.dense(7.0, 6.0, 4.0, 2.0, 1.0), 5.0),
            Row.of(Vectors.dense(8.0, 8.0, 4.0, 2.0, 1.0), 8.0),
            Row.of(Vectors.dense(9.0, 9.0, 4.0, 2.0, 1.0), 10.0));

}
