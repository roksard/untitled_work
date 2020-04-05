package roksard.concurrency.volatile_;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static roksard.concurrency.volatile_.Main.log;

public class NoVolatileSyncOnOtherObject {

    public void test(int taskCount) throws InterruptedException {
        System.out.println("\n" + getClass().getSimpleName());
        Pojo pojo = new Pojo();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < taskCount; i++) {
            exec.execute(new Runnable() {
                Integer objectForThisTask = new Integer(1);
                @Override
                public void run() {
                    log("is started.");
                    while (!pojo.stop) {
                        synchronized (objectForThisTask) {
                        }
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
