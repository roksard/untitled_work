package roksard.algorithms.dynamicProgramming.wordsearch.rxImpl;

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
        PriorityQueue<ResultWord> resultWords = new PriorityQueue<>(new Comparator<ResultWord>() {
            @Override
            public int compare(ResultWord o1, ResultWord o2) {
                return Integer.compare(o1.getCommonLength(), o2.getCommonLength());
            }
        });
        try (Scanner scanner = new Scanner(
                        Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("words_alpha.txt")),
                        StandardCharsets.UTF_8.name())
        ) {
            while (true) {
                String wordB = scanner.nextLine().trim();
                int commonLen = getCommonSubstringLen(word, wordB);
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

    /** Compares two strings' chars nth charA vs nth charB
     * returns max length of a common substring (substrings are only found when they are in same positions)
     */
    public int getCommonSubstringLen(String wordA, String wordB) {
        int length = Math.min(wordA.length(), wordB.length());
        int maxLen = 0;
        int curLen = 0;
        for (int i = 0; i < length; i++) {
            if (wordA.charAt(i) == wordB.charAt(i)) {
                curLen++;
            } else {
                if (curLen > maxLen) {
                    maxLen = curLen;
                }
                curLen = 0;
            }
        }
        if (curLen > maxLen) {
            maxLen = curLen;
        }
        return maxLen;
    }
}
