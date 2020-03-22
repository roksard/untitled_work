package roksard.concurrency.synchronization.sync_vs_lock.sync;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Pojo pojo = new Pojo();
        exec.execute(new Checker(pojo));
        exec.execute(new Mutator(pojo));
        exec.shutdown();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(pojo.getValue());
            }
        }, 1000, 1000);
    }
}
