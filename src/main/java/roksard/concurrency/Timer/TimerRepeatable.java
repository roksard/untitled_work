package roksard.concurrency.Timer;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

public class TimerRepeatable {
    private static double result = 0;
    static void activateTimer(Timer timer) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("current result: " + getResult());
            }
        }, 3000, 1000);
    }

    synchronized static void updateResult(double newResult) {
        result = newResult;
    }

    synchronized static double getResult() {
        return result;
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        activateTimer(timer);
        //some long operation
        long scale = 20000;
        double n = -scale;
        Instant start = Instant.now();
        while (n < scale) {
            n += 1d/scale;
            updateResult(n);
        }
        timer.cancel();
        System.out.println("result: " + n + ", time: " + ChronoUnit.MILLIS.between(start, Instant.now()));
    }
}
