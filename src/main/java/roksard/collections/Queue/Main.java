package roksard.collections.Queue;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedBlockingDeque<>(5);

        for(int i = 0; i < 10; i++) {
            System.out.println(queue.offer(i));
        }
        System.out.println(queue);
    }
}
