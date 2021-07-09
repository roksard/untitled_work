package roksard.concurrency.Volatile_;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static roksard.concurrency.Volatile_.Main.log;

public class NoVolatileSyncOnOtherCommonObject {

    public void test(int taskCount) throws InterruptedException {
        System.out.println("\n" + getClass().getSimpleName());
        Pojo pojo = new Pojo();
        Integer common = new Integer(1);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < taskCount; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    log("is started.");
                    while (!pojo.stop) {
                        synchronized (common) {
                            //syncing on common object between different tasks
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
