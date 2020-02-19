package roksard.datetime.dateformat_heap_usage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Random rand = new Random();
        Instant start = Instant.now();
        System.out.println("start");
        while (ChronoUnit.SECONDS.between(start, Instant.now()) < 60) {
            df.format(new Date(rand.nextLong()));
        }
        System.out.println("end");
    }
}

/*
    Heap allocations are huge (85 Gb in 60 seconds), but GC cleans that easily
    and program can run infinite time with even 8 mb heap size
 */
