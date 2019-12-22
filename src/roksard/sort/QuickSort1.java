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

    static int safeId(int max, int min, int id) {
        if (id < min) {
            return min;
        } else if (id > max) {
            return max;
        } else {
            return id;
        }
    }

    static void quickSort(int[] array, int start, int end) {
        final int arrLen = end - start + 1;
        if (arrLen < 2) {
            //return array;
        } else if (arrLen == 2) {
            if (array[end] < array[start]) {
                int buf = array[start];
                array[start] = array[end];
                array[end] = buf;
            }
            //return array;
        } else {
            int baseId = start;
            for (int i = start; i <= end; i++) {
                if (array[i] < array[baseId]) {
                    int buf = array[baseId];
                    array[baseId] = array[i];
                    baseId = i;
                    array[i] = buf;
                }
            }
            quickSort(array, start, safeId(start, end, baseId-1));
            quickSort(array, safeId(start, end, baseId+1), end);
        }
        return null;
    }
    public static void main(String[] args) {
        int[] array = new int[]{3,4,1,2,6,7,10,7,5};
        quickSort(array, 1, 1);
        System.out.println(Arrays.toString(array));
    }
}
