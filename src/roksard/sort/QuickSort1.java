package roksard.sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
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
     * moves base element (at baseId_) through the array, until all elements that are lower than base (elem < base)
     * are on the left side, and all the other elements are on the right side of base element
     * @param arrayWr (writable) - array to be modified
     * @param baseId_ id of the element considered "base" element
     * @return new baseId
     */
    static int moveBase(int[] arrayWr, int baseId_, int start, int end) {
        int baseId = baseId_;
        for (int i = start; i <= end; i++) {  // 3, 4, 1
            if (arrayWr[i] < arrayWr[baseId]) {
                int buff = arrayWr[i];
                for(int n = i; n > baseId; n--) {
                    swapElements(arrayWr, n, n-1);
                }
                arrayWr[baseId] = buff;
                baseId++;
            }
        }
        return baseId;
    }


    static void quickSort(int[] arrayWr) {
        quickSort(arrayWr, 0, arrayWr.length-1);
    }

    static void quickSort(int[] arrayWr, int start, int end) {
        final int arrLen = end - start + 1;
        if (arrLen < 2) {
            //return array;
            return;
        } else if (arrLen == 2) {
            if (arrayWr[end] < arrayWr[start]) {
                swapElements(arrayWr, start, end);
            }
            //return array;
        } else {
            int baseId = moveBase(arrayWr, start, start, end);
            quickSort(arrayWr, start, safeId(start, end, baseId-1));
            quickSort(arrayWr, safeId(start, end, baseId+1), end);
            return;
        }
        return;
    }

    public static void printArray(int[] array, int printMaxCount) {
        System.out.print("[ ");
        boolean part = false;
        for(int i = 0; i < array.length; i++) {
            if (i > printMaxCount-1) {
                part = true;
                break;
            }
            if (safeId(0, array.length-1, i) == i) {
                System.out.print(array[i] + " ");
            } else {
                part = true;
                break;
            }
        }
        if (part) {
            System.out.print("...\n");
        } else {
            System.out.print("]\n");
        }
    }

    public static void testArrayIsSorted(int[] arrayBefore, int[] arrayAfter) {
        boolean fail = false;
        fail = arrayBefore.length != arrayAfter.length;
        List<Integer> before = Arrays.stream(arrayBefore).mapToObj(i->i).collect(Collectors.toList());
        List<Integer> after = Arrays.stream(arrayAfter).mapToObj(i->i).collect(Collectors.toList());
        fail = before.stream().anyMatch(i -> !after.contains(i));

        int prev = 0;
        boolean first = true;
        for (int i : after) {
            if (first) {
                first = false;
                prev = i;
            } else {
                fail = prev > i;
            }
        }

        if (fail) {
            throw new RuntimeException("testArrayIsSorted failed");
        }
    }

    public static long testSort(int elementsCount, int elemMinValue, int elemMaxValue, int printLimit) {
        LinkedList<Integer> list = new LinkedList<>();
        Random rand = new Random();
        for (int i = 0; i < elementsCount; i++) {
            list.add(rand.nextInt(elemMaxValue - elemMinValue + 1) + elemMinValue);
        }
        int[] array = list.stream().mapToInt(i -> i).toArray();
        int[] before = Arrays.copyOf(array, array.length);
        //printArray(array, printLimit);
        long beforeTime = System.nanoTime();
        quickSort(array);
        long afterTime = System.nanoTime();
        //printArray(array, printLimit);
        testArrayIsSorted(before, array);
        return afterTime - beforeTime;
    }

    public static void testMulti(int times, int n) {
        long time = 0;
        int count = 5;
        for (int i = 0; i < count; i++) {
            time += testSort(n, 1, 100, 30);
        }
        time = time / count;
        System.out.println(n + " elements, "+times+" times, avg time: " + time);
    }
    public static void main(String[] args) {
        testMulti(5, 100);
        testMulti(5, 1000);
        testMulti(5, 10000);
        testMulti(5, 100000);
    }
}
