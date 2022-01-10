package roksard.strings;

public class StringBuilderExamples {
    static void exampleInsert() {
        StringBuilder sb = new StringBuilder();
        sb.append("a");
        sb.append("b");
        sb.insert(0, "c");
        sb.insert(0, "d");
        System.out.println(sb.toString());
    }
    public static void main(String[] args) {
        exampleInsert();
    }
}
