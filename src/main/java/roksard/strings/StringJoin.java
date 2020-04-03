package roksard.strings;

import java.util.Arrays;
import java.util.List;

public class StringJoin {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Do", "it", "right", "now", "!");
        System.out.println(String.join("\n", list));
        System.out.println(String.join(" ", list));
        System.out.println(String.join("/", list));
    }
}
