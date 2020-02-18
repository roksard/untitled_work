package roksard.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamCreatesCopy {
    public static void copyUsingStream() {
        System.out.println("copyUsingStream");
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        List<Integer> list2 = list1.stream().collect(Collectors.toList());
        System.out.println("list1 == list2: " + (list1 == list2));
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
    }
    public static void copyUsingConstructor() {
        System.out.println("copyUsingConstructor");
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        List<Integer> list2 = new ArrayList<>(list1);
        System.out.println("list1 == list2: " + (list1 == list2));
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
    }

    public static void main(String[] args) {
        copyUsingStream();
        copyUsingConstructor();
    }
}
