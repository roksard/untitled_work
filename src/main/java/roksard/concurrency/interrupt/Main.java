package roksard.concurrency.interrupt;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    static void test(Runnable task, long interruptAfter) throws InterruptedException {
        System.out.println("\n--- test:" + task + " time: " + interruptAfter + " ---");
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(task);
        Thread.sleep(interruptAfter);
        exec.shutdownNow();
        if (!exec.awaitTermination(1, TimeUnit.MILLISECONDS)) {
            System.out.println("\nstill not interrupted, force shutdown app with code 2");
            System.exit(2);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        long time = rand.nextInt(9) + 1;
        test(new TaskWithSleep(), time); //interruptable during sleep
//        test(new TaskNoSleep(), time); //does not respond to interruption
        //test(new TaskNoSleepCheckThread(), time); //checks Thread's state and responds safely to interruption
    }
}
