package roksard.datetime.formatting_in_new_javatime;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class FormattingNParsingInJavatime {
    private final static String formatPattern = "HH:mm";
    private final static String toParse = "18:15"; //in formatPattern's format

    public static void simpleTest() {
        System.out.println("--- simpleTest ---");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatPattern);
        //parsing
        TemporalAccessor haveHours = dtf.parse(toParse);
        System.out.println("hour: " + haveHours.get(ChronoField.HOUR_OF_DAY)
                + "\n min: " + haveHours.get(ChronoField.MINUTE_OF_HOUR));
        //formatting
        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC+5"));
        System.out.println("current local UTC+5 time formatted: " + dtf.format(now));
    }

    public static void threadSafetyTest() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatPattern);
        ExecutorService exec = Executors.newFixedThreadPool(100);
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t);
                e.printStackTrace();
                System.exit(5);
            }
        });
        ConcurrentLinkedQueue<String> formattedResults = new ConcurrentLinkedQueue<>();
        for (int i = 0 ; i < 1000000; i++) {
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    Random rand = new Random();
                    TemporalAccessor haveHours = dtf.parse(toParse);
                    LocalDateTime now = LocalDateTime.ofInstant(Instant.now().plus(rand.nextInt(haveHours.get(ChronoField.MINUTE_OF_HOUR)), ChronoUnit.MINUTES), ZoneId.of("UTC+5"));
                    formattedResults.add(dtf.format(now));
                }
            });
        }
        exec.shutdown();
        while(!exec.isTerminated()) {

        }
        System.out.println(formattedResults.size() + " " + formattedResults);
    }

    public static void main(String[] args) {
        threadSafetyTest();
        simpleTest();
    }
}
