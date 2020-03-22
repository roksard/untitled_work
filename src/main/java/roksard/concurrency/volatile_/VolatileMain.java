package roksard.concurrency.volatile_;

import roksard.util.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VolatileMain {
    public static void log(Object obj) {
        System.out.println(Logger.timestamp() + " " + Thread.currentThread() + " " + obj);
    }

    public static void testVolatile(int taskCount) throws InterruptedException {
        System.out.println("\ntestVolatile");
        Pojo pojo = new Pojo();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < taskCount; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    log("is started.");
                    while (!pojo.stopVolatile) {
                    }
                    log("is stopped.");
                }
            });
        }
        Thread.sleep(2000);
        exec.execute(new Runnable() {
            @Override
            public void run() {
                log("sending stop.");
                pojo.stopVolatile = true;
            }
        });
        exec.shutdown();
        if (!exec.awaitTermination(2000, TimeUnit.MILLISECONDS)) {
            System.out.println("threads stil running.. shutting down app with code 1");
            System.exit(1);
        }
    }
    public static void test(int taskCount) throws InterruptedException {
        System.out.println("\ntest (not volatile)");
        Pojo pojo = new Pojo();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < taskCount; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    log("is started.");
                    while (!pojo.stop) {
                    }
                    log("is stopped.");
                }
            });
        }
        Thread.sleep(2000);
        exec.execute(new Runnable() {
            @Override
            public void run() {
                log("sending stop.");
                pojo.stop = true;
            }
        });
        exec.shutdown();
        if (!exec.awaitTermination(2000, TimeUnit.MILLISECONDS)) {
            log("threads stil running.. shutting down app with code 1");
            System.exit(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        testVolatile(20);
        test(20);
    }
}

/* Output:
21:21:13.927 Thread[pool-2-thread-15,5,main] is started.
21:21:13.927 Thread[pool-2-thread-18,5,main] is started.
21:21:15.509 Thread[pool-2-thread-21,5,main] sending stop.
21:21:17.352 Thread[main,5,main] threads stil running.. shutting down app with code 1
//threads dont see that variable has been changed by another thread so they keep running forever (unless the variable is volatile)
 */