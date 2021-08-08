package roksard.algorithms.dynamicProgramming.wordsearch.grokkingAlgorithms;

public class WordSearch {
    public static void main(String[] args) {
        System.out.println("seq: " + new WordSearch().getCommonSequence("abcabc", "abcdef", true));
    }

    public int getCommonSequence(String wordA, String wordB, boolean debugPrint) {
        int size = Math.min(wordA.length(), wordB.length());
        int[][] data = new int[size][size];
        int totalSequence = 0;
        for (int i = 0; i < size; i++) {
            boolean sequenceContinued = false;
            int currentMax = 0;
            for (int n = 0; n < size; n++) {
                int currentSequence = 0;
                if (i > 0 && n > 0) {
                    currentSequence = data[i-1][n-1];
                }
                boolean charsEqual = wordA.charAt(i) == wordB.charAt(n);
                if (charsEqual) {
                    sequenceContinued = currentSequence > 0;
                    currentSequence += 1;
                    currentMax = Math.max(currentMax, currentSequence);
                }
                data[i][n] = charsEqual ? currentSequence : 0;
            }
            //sequence broke, adding its size to totalSequence
            if (!sequenceContinued || i == size-1) {
                totalSequence += currentMax;
                currentMax = 0;
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
