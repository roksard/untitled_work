package roksard.stringjoiner;

public class StringJoiner2 {
    public static void main(String[] args) {
        String[] array = {
                "one",
                "word",
                null,
                "to",
                "say",
                "",
                "end"
        };
        System.out.println(String.join(", ", array));

    }
}
