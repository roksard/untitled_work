package roksard.concurrency.volatile_;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static roksard.concurrency.volatile_.Main.log;

public class Volatile {
    public void test(int taskCount) throws InterruptedException {
        System.out.println("\n" + getClass().getSimpleName());
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
}
