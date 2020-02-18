package roksard.concurrency.locks_explicit;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class FibCalculator {
    private ReentrantLock lock = new ReentrantLock();

    private List<Long> fibonacci = new ArrayList<>();

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void hardCalc(long hardness) {
        Instant inst1 = Instant.now();
        double a = -hardness;
        double step = 1/(double)hardness;
        while(a < hardness) {
            a += step;
        }
        //System.out.println("hardCalc" + ChronoUnit.MILLIS.between(Instant.now(), inst1));
    }

    public List<Long> getFibonacci(int length) {
        lock.lock();
        try {
            if (fibonacci.size() == length) {
                return fibonacci;
            } else {
                fibonacci.clear();
                long prev1 = 0;
                long prev2 = 0;
                while (fibonacci.size() < length) {
                    hardCalc(4999);
                    long toAdd = 0;
                    if (fibonacci.size() == 0) {
                        toAdd = 0;
                    } else if (fibonacci.size() <= 2) {
                        toAdd = 1;
                    } else {
                        toAdd = prev1 + prev2;
                    }
                    prev2 = prev1;
                    prev1 = toAdd;
                    fibonacci.add(toAdd);
                }

            }
            return new ArrayList<>(fibonacci);
        } finally {
            lock.unlock();
        }
    }

    public List<Long> getFibonacciNoLock(int length) {
        if (fibonacci.size() == length) {
            return fibonacci;
        } else {
            fibonacci.clear();
            long prev1 = 0;
            long prev2 = 0;
            while (fibonacci.size() < length) {
                hardCalc(4999);
                long toAdd = 0;
                if (fibonacci.size() == 0) {
                    toAdd = 0;
                } else if (fibonacci.size() <= 2) {
                    toAdd = 1;
                } else {
                    toAdd = prev1 + prev2;
                }
                prev2 = prev1;
                prev1 = toAdd;
                fibonacci.add(toAdd);
            }

        }
        return new ArrayList<>(fibonacci);
    }
}
