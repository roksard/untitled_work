package roksard.strings;

import java.util.Arrays;
import java.util.LinkedList;

public class WordCount {
    public static String decor(String input) {
        return "=== " + input + " ===";
    }
    public static void main(String[] args) {
        String content = "Jetzt ich will über meine persönlichen Erfahrungen erzählen. Vor 3 Jahren hatte ich eine Spinne. Die war ein wenig gefährlich und sehr furchtbar. Unter anderem meine Spinne war sehr schön. Ich sah gerne jeden Tag wie meine Spinne gelebt in ihr Haus. Und die Spinne kann normal 1 Mal pro Woche essen. Ich hatte viel Spaß mit der, aber vor 2 Jahren ist meine Spinne gestorben.\n" +
                "\t3. In meinem Heimatland Russland ist es gewöhnlich um 1 oder 2 Tiere zu Hause zu haben. Einige Leute am liebsten wählen eine Katze. Die anderen Leute mögen sehr gerne einen kleinen Hund.  Einigle Leute mögen auch einen großen Hund zu haben.  Ich glaube mehr als 50 Prozent von Familien in Russland haben ein Haustier.\n" +
                "\t4. Jetzt will ich die Vor- und Nachteile nennen. Auf einer Seite die Haustiere bringen viel Spaß und insbesondere, wenn es Kinder gibt. Auf der anderen Seite die Haustiere manchmal brauchen auch viel Beachten und nicht alle Familien haben genug Zeit für das.\n" +
                "\tMeine Meinung über das Thema kommt jetzt. Ich denke die Leute soll sich entscheiden und denken darauf, ob sie können genug Zeit für die Haustiere bieten. Leute soll denken vorher nehmen neue Haustiere zu.";

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
