package roksard.algorithms.dynamicProgramming.wordsearch.grokkingAlgorithms;

public class WordSearch {
    public static void main(String[] args) {
        System.out.println("seq: " + new WordSearch().getCommonSequence("funken", "fucken", true));
    }

    public int getCommonSequence(String wordA, String wordB, boolean debugPrint) {
        int size = Math.min(wordA.length(), wordB.length());
        int[][] data = new int[size][size];
        int totalSequence = 0;
        for (int i = 0; i < size; i++) {
            boolean sequenceContinued = false;
            int currentSequence = 0;
            if (i > 0) {
                currentSequence = data[i-1][i-1];
            }
            boolean charsEqual = wordA.charAt(i) == wordB.charAt(i);
            if (charsEqual) {
                sequenceContinued = currentSequence > 0;
                currentSequence += 1;
            }
            data[i][i] = charsEqual ? currentSequence : 0;
            //sequence broke, adding its size to totalSequence
            if (!charsEqual || i == size-1) {
                totalSequence += currentSequence;
            }
        }

        //region debugPrint
        if (debugPrint) {
            System.out.print("  ");
            for (int n = 0; n < size; n++) {
                System.out.print(wordB.charAt(n) + " ");
            }
            System.out.println();

            for (int i = 0; i < size; i++) {
                System.out.print(wordA.charAt(i) + " ");
                for (int n = 0; n < size; n++) {
                    System.out.print(data[i][n] + " ");
                }
                System.out.println();
            }
        }
        //endregion debugPrint
        return totalSequence;
    }
}
