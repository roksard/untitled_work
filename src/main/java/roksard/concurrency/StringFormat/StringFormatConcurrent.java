package roksard.concurrency.StringFormat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StringFormatConcurrent {
    public static void main(String[] args) {
        final int x = 33;
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.err.println(t + " @ " + e);
                System.exit(1);
            }
        });
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    while (!Thread.interrupted()) {
                        String s = String.format("%d %s", x, this.toString()); //safe
                        if (s.charAt(s.length()-1) == 'Z') {
                            throw new RuntimeException("elo");
                        }
                    }
                }
            });
        }
    }
}
