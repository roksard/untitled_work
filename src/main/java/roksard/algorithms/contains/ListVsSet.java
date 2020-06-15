package roksard.algorithms.contains;

import java.util.*;

public class ListVsSet {
    private static void test(int listASize, List<Long> listB, boolean useSet) {
        System.out.println("test " + listASize + " " + listB.getClass().getSimpleName() + " useSet: " + useSet);
        List<Long> listA = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < listASize; i++) {
            listA.add(rand.nextLong());
            listB.add(rand.nextLong());
        }
        long x = 0L;
        for (Long a : listA) {
            x += listB.contains(a) ? 1L : -1L;
        }
        long start = System.nanoTime();
        if (useSet) {
            Set<Long> set = new HashSet<>(listB);
            for (Long a : listA) {
                x += set.contains(a) ? 1L : -1L;
            }
        } else {
            for (Long a : listA) {
                x += listB.contains(a) ? 1L : -1L;
            }
        }
        long end = System.nanoTime();
        System.out.println("time: " + ((end - start)/10000) + " result: " + x);
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            test(10000, new ArrayList<>(), true);
        System.out.println("==================");
        int n = 100;
        for (int i = 0; i < 3; i++) {
            test(n, new ArrayList<>(), false);
        }
        for (int i = 0; i < 3; i++) {
            test(n, new LinkedList<>(), false);
        }
        for (int i = 0; i < 3; i++) {
            test(n, new ArrayList<>(), true);
        }
        for (int i = 0; i < 3; i++) {
            test(n, new LinkedList<>(), true);
        }
    }
}

/*
    OUtput:
        using Set greatly improves performance but only when n > 1000 (looking 1 element in a 1000 size array)

 */