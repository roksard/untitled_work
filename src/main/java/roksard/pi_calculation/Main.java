package roksard.pi_calculation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });
        GregoryLeibnizPi task = new GregoryLeibnizPi();
        executor.execute(task);
        executor.shutdown();
        while (!executor.isTerminated()) {
            System.out.println(task.getPi() + String.format(" (%07.3f)", task.getProgress()));
            Thread.sleep(1000);
        }
        System.out.println(task.getPi() + String.format(" (%f)", task.getProgress()));
    }

}
