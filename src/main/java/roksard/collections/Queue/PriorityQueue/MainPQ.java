package roksard.collections.Queue.PriorityQueue;

import java.util.Arrays;
import java.util.PriorityQueue;

class DataClass implements Comparable<DataClass> {
    int value;
    public DataClass(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(DataClass o) {
        return -Integer.compare(this.value, o.value);
    }

    @Override
    public String toString() {
        return "D:"+value;
    }
}

public class MainPQ {
    static void printPriorityQueue(PriorityQueue<?> que) {
        Object d;
        while ((d = que.poll()) != null) {
            System.out.println(d);
        }
    }
    public static void main(String[] args) {
        PriorityQueue<DataClass> que = new PriorityQueue<>();
        que.addAll(Arrays.asList(new DataClass(1), new DataClass(0), new DataClass(5), new DataClass(3)));
        DataClass changeable = new DataClass(4);
        que.offer(changeable);
        que.offer(new DataClass(6));
        que.offer(new DataClass(2));
        que.forEach(System.out::println); //not sorted
        System.out.println("\nUsing poll()");
        //printPriorityQueue(que);
        //changeable.value = 10;
        //System.out.println("\nChanged some value 4 to 10"); //cannot change value when its already in the queue
        printPriorityQueue(que);
    }
}
