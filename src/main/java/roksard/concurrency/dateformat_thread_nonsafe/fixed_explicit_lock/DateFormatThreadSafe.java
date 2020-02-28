package roksard.concurrency.dateformat_thread_nonsafe.fixed_explicit_lock;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class DateFormatThreadSafe {
    private final SimpleDateFormat f;
    private final ReentrantLock lock = new ReentrantLock();

    public DateFormatThreadSafe(String format) {
        f = new SimpleDateFormat(format);
    }

    public FormatResult format (Date date) {
        Instant before = Instant.now();
        lock.lock();
        try {
            long time = ChronoUnit.MILLIS.between(before, Instant.now());
            String result = f.format(date);
            return new FormatResult(result, time);
        } finally {
            lock.unlock();
        }
    }
}
