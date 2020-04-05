package roksard.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadFactory;

public class Util {
    public static class Concurrency {
        public static ThreadFactory getThreadFactoryExitOnUncaught() {
            return new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                        @Override
                        public void uncaughtException(Thread t, Throwable e) {
                            System.err.println("Uncaught exception: ");
                            System.err.println(t);
                            System.err.println(e);
                            System.exit(1);
                        }
                    });
                    return t;
                }
            };
        }
    }

    public static void sleepn(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String timestamp() {
        Instant time = Instant.now();
        return DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(ZonedDateTime.ofInstant(time,
                ZoneId.systemDefault()));
    }

    public static double rangeLimit(double from, double to, double value) {
        double result = value;
        if (result < from)
            result = from;
        else if(result > to)
            result = to;
        return result;
    }

    public static double randomDouble(double from, double to) {
        return randomDouble(from, to, new Random());
    }

    public static double randomDouble(double from, double to, Random rand) {
        double delta = to - from;
        return from + delta * rand.nextDouble();
    }
}
