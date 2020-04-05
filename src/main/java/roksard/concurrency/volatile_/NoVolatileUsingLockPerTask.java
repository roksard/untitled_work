package roksard.concurrency.volatile_;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static roksard.concurrency.volatile_.Main.log;

public class NoVolatileUsingLockPerTask {

    public void test(int taskCount) throws InterruptedException {
        System.out.println("\n" + getClass().getSimpleName());
        Pojo pojo = new Pojo();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < taskCount; i++) {
            exec.execute(new Runnable() {
                Lock lock = new ReentrantLock();
                @Override
                public void run() {
                    log("is started.");
                    while (!pojo.stop) {
                        lock.lock();
                        lock.unlock();
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
}
