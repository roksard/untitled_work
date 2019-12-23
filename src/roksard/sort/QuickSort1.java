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

    static int safeId(int min, int max, int id) {
        if (id < min) {
            return min;
        } else if (id > max) {
            return max;
        } else {
            return id;
        }
    }

    static void swapElements(Object array_, int aIndex, int bIndex) {
        boolean ok = false;
        if (array_ instanceof int[]) {
            int[] array = (int[])array_;
            if (aIndex >= 0 && aIndex <= array.length
                    && bIndex >= 0 && bIndex <= array.length
            ) {
                int buff = array[aIndex];
                array[aIndex] = array[bIndex];
                array[bIndex] = buff;
                ok = true;
            }
        }
        if (!ok) {
            throw new RuntimeException("ArraySwapException");
        }
    }

    /**
     *
     * @param array
     * @param baseId_
     * @return new baseId
     */
    static int moveBase(int[] array, int baseId_, int start, int end) {
        int baseId = baseId_;
        for (int i = start; i <= end; i++) {  // 3, 4, 1
            if (array[i] < array[baseId]) {
                int buff = array[i];
                for(int n = i; n > baseId; n--) {
                    swapElements(array, n, n-1);
                }
                array[baseId] = buff;
                baseId++;
            }
        }
        return baseId;
    }

    static void quickSort(int[] array) {
        quickSort(array, 0, array.length-1);
    }

    static void quickSort(int[] array, int start, int end) {
        final int arrLen = end - start + 1;
        if (arrLen < 2) {
            //return array;
            return;
        } else if (arrLen == 2) {
            if (array[end] < array[start]) {
                swapElements(array, start, end);
            }
            //return array;
        } else {
            int baseId = moveBase(array, start, start, end);
            quickSort(array, start, safeId(start, end, baseId-1));
            quickSort(array, safeId(start, end, baseId+1), end);
            return;
        }
        return;
    }
    public static void main(String[] args) {
        int[] array = new int[]{100,1,1,2,6,7,10,7,5};
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
