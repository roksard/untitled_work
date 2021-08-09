package roksard.algorithms.cosineSimilarity;

public class CosineSimilarity {
    public static void main(String[] args) {
        int[] objectA = new int[] {3,2,0,5,0,0,2,0,0};
        int[] objectB = new int[] {1,0,0,0,0,0,1,0,2};
        System.out.println("cosine similarity: " + new CosineSimilarity().calc(objectA, objectB));
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
        System.out.println("mult = " + mult);
        System.out.println("magnitudeA = " + magnitudeA);
        System.out.println("magnitudeB = " + magnitudeB);
        return mult / (magnitudeA * magnitudeB);
    }
}
