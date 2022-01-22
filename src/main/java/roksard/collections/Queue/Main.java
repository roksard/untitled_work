package roksard.collections.Queue;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> dequeue = new LinkedBlockingDeque<>(5);

        for(int i = 0; i < 10; i++) {
            System.out.println(dequeue.offerFirst(i));
            System.out.println(dequeue);
        }

        System.out.println("--- removing ---");

        while (!dequeue.isEmpty()) {
            System.out.println(dequeue.pollLast());
            System.out.println(dequeue);
        }
    }
}
