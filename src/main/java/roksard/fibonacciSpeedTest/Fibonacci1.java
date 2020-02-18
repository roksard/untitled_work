package roksard.fibonacciSpeedTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * results:
 * multi test: count=5, size=10
 * fibonacciLinkedList
 * [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
 * time: 66620
 * fibonacciArrayList
 * [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
 * time: 6880
 * fibonacciArrayListUnknownSize
 * [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
 * time: 6300
 * fibonacciLinkedList2
 * [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
 * time: 5520
 * fibonacciLinkedList3
 * [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
 * time: 31440
 * fibonacciLinkedList_cached
 * [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
 * time: 3300
 * fibonacciArrayList_cached
 * [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
 * time: 2880
 * fibonacciArrayList_cachedNoSize
 * [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
 * time: 3260
 *
 * multi test: count=5, size=10000000
 * fibonacciLinkedList
 * time: 3 614 468 560
 * fibonacciArrayList
 * time:   920 307 740
 * fibonacciArrayListUnknownSize
 * time: 1 734 575 440
 * fibonacciLinkedList2
 * time: 2 569 811 360
 * fibonacciLinkedList3
 * time: 2 976 012 500
 * fibonacciLinkedList_cached
 * time: 2 265 622 320
 * fibonacciArrayList_cached
 * time: 1 008 987 700
 * fibonacciArrayList_cachedNoSize
 * time: 1 136 602 280
 */

public class Fibonacci1 {
    public static void main(String[] args) {
        test(5, 10, true);
        test(5, 10_000_000, false);
    }

    public static void test(int count, int size, boolean printList) {
        System.out.println("multi test: count="+ count + ", size="+size);
        List<Long> list = null;
        long start = 0;
        long end = 0;

        //test#1
        start = System.nanoTime();
        for(int i = 0; i < count; i++) {
            list = fibonacciLinkedList(size);
        }
        end = System.nanoTime();
        System.out.println("fibonacciLinkedList");
        if (printList) System.out.println(list);
        System.out.println("time: "+ (end - start) / count);

        //test#2
        start = System.nanoTime();
        for(int i = 0; i < count; i++) {
            list = fibonacciArrayList(size);
        }
        end = System.nanoTime();
        System.out.println("fibonacciArrayList");
        if (printList) System.out.println(list);
        System.out.println("time: "+ (end - start) / count);

        //test#3
        start = System.nanoTime();
        for(int i = 0; i < count; i++) {
            list = fibonacciArrayListUnknownSize(size);
        }
        end = System.nanoTime();
        System.out.println("fibonacciArrayListUnknownSize");
        if (printList) System.out.println(list);
        System.out.println("time: "+ (end - start) / count);

        //test#4
        start = System.nanoTime();
        for(int i = 0; i < count; i++) {
            list = fibonacciLinkedList2(size);
        }
        end = System.nanoTime();
        System.out.println("fibonacciLinkedList2");
        if (printList) System.out.println(list);
        System.out.println("time: "+ (end - start) / count);

        //test#5
        start = System.nanoTime();
        for(int i = 0; i < count; i++) {
            list = fibonacciLinkedList3(size);
        }
        end = System.nanoTime();
        System.out.println("fibonacciLinkedList3");
        if (printList) System.out.println(list);
        System.out.println("time: "+ (end - start) / count);

        //cached: 6, 7, 8
        //test#6
        start = System.nanoTime();
        for(int i = 0; i < count; i++) {
            list = fibonacciLinkedList_cached(size);
        }
        end = System.nanoTime();
        System.out.println("fibonacciLinkedList_cached");
        if (printList) System.out.println(list);
        System.out.println("time: "+ (end - start) / count);

        //test#7
        start = System.nanoTime();
        for(int i = 0; i < count; i++) {
            list = fibonacciArrayList_cached(size);
        }
        end = System.nanoTime();
        System.out.println("fibonacciArrayList_cached");
        if (printList) System.out.println(list);
        System.out.println("time: "+ (end - start) / count);

        //test#8
        start = System.nanoTime();
        for(int i = 0; i < count; i++) {
            list = fibonacciArrayList_cachedNoSize(size);
        }
        end = System.nanoTime();
        System.out.println("fibonacciArrayList_cachedNoSize");
        if (printList) System.out.println(list);
        System.out.println("time: "+ (end - start) / count);
    }

    public static LinkedList<Long> fibonacciLinkedList(int size) {
        LinkedList<Long> fibonacci = new LinkedList<>();
        for(int i = 0; i < size; i++) {
            if (fibonacci.isEmpty() || fibonacci.size() == 1) {
                fibonacci.add(1L);
            } else {
                Long last = fibonacci.removeLast();
                Long subLast = fibonacci.getLast();
                long new_ = last + subLast;
                fibonacci.add(last);
                fibonacci.add(new_);
            }
        }
        return fibonacci;
    }

    public static LinkedList<Long> fibonacciLinkedList2(int size) {
        LinkedList<Long> fibonacci = new LinkedList<>();
        for(int i = 0; i < size; i++) {
            if (fibonacci.isEmpty() || fibonacci.size() == 1) {
                fibonacci.add(1L);
            } else {
                Long last = fibonacci.removeLast();
                long new_ = last + fibonacci.getLast();
                fibonacci.add(last);
                fibonacci.add(new_);
            }
        }
        return fibonacci;
    }

    public static LinkedList<Long> fibonacciLinkedList3(int size) {
        LinkedList<Long> fibonacci = new LinkedList<>();
        for(int i = 0; i < size; i++) {
            if (fibonacci.isEmpty() || fibonacci.size() == 1) {
                fibonacci.add(1L);
            } else {
                Iterator<Long> descIter = fibonacci.descendingIterator();
                Long last = descIter.next();
                Long subLast = descIter.next();
                long new_ = last + subLast;
                fibonacci.add(new_);
            }
        }
        return fibonacci;
    }

    public static LinkedList<Long> fibonacciLinkedList_cached(int size) {
        LinkedList<Long> fibonacci = new LinkedList<>();
        long last = 1;
        long subLast = 1;
        long new_ = 0;
        for(int i = 0; i < size; i++) {
            if (fibonacci.isEmpty() || fibonacci.size() == 1) {
                fibonacci.add(1L);
            } else {
                new_ = last + subLast;
                subLast = last;
                last = new_;
                fibonacci.add(new_);
            }
        }
        return fibonacci;
    }

    public static ArrayList<Long> fibonacciArrayList_cached(int size) {
        ArrayList<Long> fibonacci = new ArrayList<>(size);
        long last = 1;
        long subLast = 1;
        long new_ = 0;
        for(int i = 0; i < size; i++) {
            if (fibonacci.isEmpty() || fibonacci.size() == 1) {
                fibonacci.add(1L);
            } else {
                new_ = last + subLast;
                subLast = last;
                last = new_;
                fibonacci.add(new_);
            }
        }
        return fibonacci;
    }

    public static ArrayList<Long> fibonacciArrayList_cachedNoSize(int size) {
        ArrayList<Long> fibonacci = new ArrayList<>();
        long last = 1;
        long subLast = 1;
        long new_ = 0;
        for(int i = 0; i < size; i++) {
            if (fibonacci.isEmpty() || fibonacci.size() == 1) {
                fibonacci.add(1L);
            } else {
                new_ = last + subLast;
                subLast = last;
                last = new_;
                fibonacci.add(new_);
            }
        }
        return fibonacci;
    }

    public static ArrayList<Long> fibonacciArrayList(int size) {
        ArrayList<Long> result = new ArrayList<>(size); //preallocation for known size
        for (int i = 0; i < size; i++) {
            if (result.isEmpty() || result.size() == 1) {
                result.add(1L);
            } else {
                int last = result.size() -1 ;
                result.add(result.get(last) + result.get(last-1));
            }
        }
        return result;
    }

    public static ArrayList<Long> fibonacciArrayListUnknownSize(int size) {
        ArrayList<Long> result = new ArrayList<>(); //default size allocation
        for (int i = 0; i < size; i++) {
            if (result.isEmpty() || result.size() == 1) {
                result.add(1L);
            } else {
                int last = result.size() -1 ;
                result.add(result.get(last) + result.get(last-1));
            }
        }
        return result;
    }
}
