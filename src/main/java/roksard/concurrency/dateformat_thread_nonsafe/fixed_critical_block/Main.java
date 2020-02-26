package roksard.concurrency.dateformat_thread_nonsafe.fixed_critical_block;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static long max = 0;
    public static synchronized void updateMax(long time) {
        if (time > max) {
            max = time;
        }
    }

    public static void main(String[] args) {
        final DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        ExecutorService ex = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10000; i++) {
            ex.execute(new Runnable() {
                public void run() {
                    try {
                        String formatResult;
                        Date date = new Date(new Random().nextLong());
                        Instant before = Instant.now();
                        synchronized (f) {
                            formatResult = f.format(date);
                        }
                        long time = ChronoUnit.MILLIS.between(before, Instant.now());;
                        updateMax(time);
                        if (time > 0) {
                            System.out.print(formatResult + "(" + time + "ms)" + ", ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                };
            });
        }
        ex.shutdown();
        while (!ex.isTerminated()) {

        }
        System.out.println("\nmax lock waiting time: " + max + " ms");
    }

}
