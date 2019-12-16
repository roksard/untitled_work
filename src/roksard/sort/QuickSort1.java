package roksard.sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class QuickSort1 {
    public static List<Integer> quickSort(List<Integer> list) {
        if (list.size() < 2) {
            return list;
        } else if (list.size() == 2) {
            if (list.get(0) > list.get(1)) {
                LinkedList<Integer> arr = new LinkedList<>();
                arr.add(list.get(1));
                arr.add(list.get(0));
                return arr;
            } else {
                return new LinkedList<>(list);
            }
        } else {
            int base = list.get(0);
            List<Integer> before = list.stream().filter(item -> item < base).collect(Collectors.toList());
            List<Integer> after = list.stream().filter(item -> item > base).collect(Collectors.toList());
            before = quickSort(before);
            after = quickSort(after);
            List<Integer> all = new LinkedList<>();
            all.addAll(before);
            all.add(base);
            all.addAll(after);
            return all;
        }
    }

    int[] quickSort(int[] array) {
        if (array.length < 2) {
            return array;
        } else if (array.length == 2) {
            if (array[1] < array[0]) {
                int buf = array[0];
                array[0] = array[1];
                array[1] = buf;
            }
            return array;
        } else {
            int base = array[0];
            int beforeIdMax = 0;
            int afterIdMax = 0;

        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(quickSort(Arrays.asList(5, 4, 3, 2, 10, 15, 1, 7)));
    }
}
