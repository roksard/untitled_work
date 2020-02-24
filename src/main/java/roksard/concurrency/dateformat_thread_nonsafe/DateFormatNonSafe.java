package roksard.concurrency.dateformat_thread_nonsafe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DateFormatNonSafe {
    public static void main(String[] args) {
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        ExecutorService ex = Executors.newFixedThreadPool(5);
        for (;;) {
            ex.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.print(f.format(new Date(new Random().nextLong())) + ", ");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                };
            });
        }
    }

}
