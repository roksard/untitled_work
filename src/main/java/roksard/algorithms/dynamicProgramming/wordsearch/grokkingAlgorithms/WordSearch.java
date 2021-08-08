package roksard.algorithms.dynamicProgramming.wordsearch.grokkingAlgorithms;

import roksard.algorithms.dynamicProgramming.wordsearch.rxImpl.ResultWord;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordSearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word;
        System.out.println("Type in a word or 'q' to quit");
        while (!(word = scanner.nextLine()).equals("q")) {
            System.out.println(
                    "similar words: " +
                            new WordSearch().findSimilarWords(word, 10)
            );
            System.out.println("Type in a word or 'q' to quit");
        }
    }

    public List<String> findSimilarWords(String word, int wordLimit) {
        PriorityQueue<ResultWord> resultWords = new PriorityQueue<>(Comparator.comparingInt(ResultWord::getCommonLength));
        try (Scanner scanner = new Scanner(
                Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("words_alpha.txt")),
                StandardCharsets.UTF_8.name())
        ) {
            while (true) {
                String wordB = scanner.nextLine().trim();
                int commonLen = getCommonSequence(word, wordB, DebugType.NONE);
                if (commonLen > 0) {
                    resultWords.add(new ResultWord(commonLen, wordB));
                    if (resultWords.size() > wordLimit) {
                        resultWords.remove();
                    }
                }
            }
        } catch (NoSuchElementException e) {
            //end of file
        }

        List<String> result = new ArrayList<>();
        ResultWord poll;
        while ((poll = resultWords.poll()) != null) {
            result.add(poll.getWord());
        }
        Collections.reverse(result);

        return result;
    }


    static enum DebugType {NONE, ALL, ONLY_MATCHING;}
    public int getCommonSequence(String wordA, String wordB, DebugType debugType) {
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
        if (debugType.equals(DebugType.ALL) || debugType.equals(DebugType.ONLY_MATCHING) && totalSequence > 0) {
            System.out.print("  ");
            for (int n = 0; n < wordB.length(); n++) {
                System.out.print(wordB.charAt(n) + " ");
            }
            System.out.println();

            for (int i = 0; i < wordA.length(); i++) {
                System.out.print(wordA.charAt(i) + " ");
                if (i < size) {
                    for (int n = 0; n < size; n++) {
                        System.out.print(data[i][n] + " ");
                    }
                }
                System.out.println();
            }
            System.out.println("common sequence: " + totalSequence);
        }
        //endregion debugPrint
        return totalSequence;
    }
}
