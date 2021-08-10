package roksard.algorithms.cosineSimilarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ignore length/size of a vector, takes into account only direction of 2 vectors
 * resulting a value between 0 and 1. where 1 is 100% equal direction und vice versa
 */
public class CosineSimilarity {
    public static void main(String[] args) {
        int[] objectA;
        int[] objectB;
        List<ObjectDuplex> examples = new ArrayList<>();
        examples.add(new ObjectDuplex(new int[]{3, 2, 0, 5, 0, 0, 2, 0, 0}, new int[]{1, 0, 0, 0, 0, 0, 1, 0, 2}));
        examples.add(new ObjectDuplex(new int[]{5, 5}, new int[]{5, 5}));
        examples.add(new ObjectDuplex(new int[]{99, 99}, new int[]{1, 1}));
        examples.add(new ObjectDuplex(new int[]{9, 9}, new int[]{1, 1}));
        examples.add(new ObjectDuplex(new int[]{1, 1}, new int[]{9, 9}));
        examples.add(new ObjectDuplex(new int[]{-9, -9}, new int[]{9, 9}));
        examples.add(new ObjectDuplex(new int[]{1, 8}, new int[]{1, 0}));
        examples.add(new ObjectDuplex(new int[]{1, 8}, new int[]{0, 8}));
        for (ObjectDuplex example : examples) {
            System.out.format("cos-sim: %s | objectA: %s | objectB: %s\n",
                    new CosineSimilarity().calc(example.getObjectA(), example.getObjectB()),
                    Arrays.toString(example.getObjectA()),
                    Arrays.toString(example.getObjectB())
            );
        }
    }

    public double calc(int[] a, int[] b) {
        double mult = 0;
        double magnitudeA = 0;
        double magnitudeB = 0;
        for(int i = 0; i < a.length; i++) {
            mult += a[i] * b[i];
            magnitudeA += a[i] * a[i];
            magnitudeB += b[i] * b[i];
        }
        magnitudeA = Math.sqrt(magnitudeA);
        magnitudeB = Math.sqrt(magnitudeB);
//        System.out.println("mult = " + mult);
//        System.out.println("magnitudeA = " + magnitudeA);
//        System.out.println("magnitudeB = " + magnitudeB);
        return mult / (magnitudeA * magnitudeB);
    }
}
