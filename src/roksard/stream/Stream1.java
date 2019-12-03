package roksard.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Stream1 {
    public static void main(String[] args) {
        List<String> list1 = new LinkedList<>();
        List<String> list2 = new ArrayList<>();

        list1.addAll(Arrays.asList(new String[] {"ich", "du", "er", "sie"}));
        list2.addAll(Arrays.asList(new String[] {"wir", "ihr", "Sie"}));

        Stream.concat(list1.stream(), list2.stream())
                .forEach(str -> System.out.println(str));

    }
}
