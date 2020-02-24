package roksard.concurrency.dateformat_thread_nonsafe.fixed;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class DafeFormatThreadSafe {
    private final SimpleDateFormat f;
    private final ReentrantLock lock = new ReentrantLock();

    public DafeFormatThreadSafe(String format) {
        f = new SimpleDateFormat(format);
    }

    public String format (Date date) {
        lock.lock();
        try {
            String result = f.format(date);
            return result;
        } finally {
            lock.unlock();
        }
    }
}
