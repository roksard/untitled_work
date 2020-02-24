package roksard.concurrency.dateformat_thread_nonsafe.fixed;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DateFormatNonSafe_Fixed {
    private static long max = 0;
    public static synchronized void updateMax(long time) {
        if (time > max) {
            max = time;
            System.out.println("new max:" + time);
        }
    }

    public static void main(String[] args) {
        final DafeFormatThreadSafe f = new DafeFormatThreadSafe("yyyy-MM-dd");
        ExecutorService ex = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 10; i++) {
            ex.execute(new Runnable() {
                public void run() {
                    try {
                        Instant before = Instant.now();
                        String dateFormatted = f.format(new Date(new Random().nextLong()));
                        long time = ChronoUnit.MILLIS.between(before, Instant.now());
                        updateMax(time);
                        System.out.print(dateFormatted + "(" + time + "ms)" + ", ");
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
