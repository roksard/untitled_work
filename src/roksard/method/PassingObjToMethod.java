package roksard.method;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PassingObjToMethod {
    public static List<Integer> modifyingOriginal(final List<Integer> list) {
        list.add(1);
        list.add(2);
        return list;
    }

    public static List<Integer> reassignOriginal(List<Integer> list) {
        list = list.stream()
                .filter(item -> item % 2 == 0)
                .collect(Collectors.toList());
        return list;
    }
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        System.out.println("modifyingOriginal: ");
        List<Integer> list1After = modifyingOriginal(list1);
        System.out.println("list1 == list1After: " + (list1 == list1After));
        System.out.println("list1: " + list1);
        System.out.println("list1After: " + list1After);
        System.out.println();

        list1.clear();
        list1After.clear();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        System.out.println("reassignOriginal: ");
        list1After = reassignOriginal(list1);
        System.out.println("list1 == list1After: " + (list1 == list1After));
        System.out.println("list1: " + list1);
        System.out.println("list1After: " + list1After);
        System.out.println();

    }
}
