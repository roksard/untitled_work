package roksard.strings;

import java.util.Arrays;
import java.util.LinkedList;

public class WordCount {
    public static String decor(String input) {
        return "=== " + input + " ===";
    }
    public static void main(String[] args) {
        String content = "Meine Auto wird repariert.";

        content = content.replaceAll("[ ,.\\-\\+\\n]+", " ");
        System.out.println(content);

        String[] words = content.split(" ");
        LinkedList<String> filteredWords = new LinkedList<>();
        long count = Arrays.stream(words).filter(word -> {
            if (word.length() >= 2) {
                return true;
            } else {
                if (!word.isEmpty()) {
                    filteredWords.add("  '" + word + "'");
                }
                return false;
            }}).count();
        System.out.println(decor("Words count: " + count));
        System.out.println(decor("Filtered words: "));
        filteredWords.stream().forEach(System.out::println);
        System.out.println(decor("end filtered words"));
    }
}
