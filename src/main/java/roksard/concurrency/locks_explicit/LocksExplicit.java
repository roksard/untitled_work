package roksard.concurrency.locks_explicit;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class LocksExplicit {
    public static boolean validateFib(List<Long> fib) {
        long prev1 = 0;
        long prev2 = 0;
        for (int i = 0; i < fib.size(); i++) {
            long toAdd = 0;
            if (i == 0) {
                toAdd = 0;
                if (fib.get(i) != toAdd) {
                    System.out.println("Invalid: " + fib.get(i) + " != " + toAdd + " at pos " + i);
                    return false;
                }
            } else if (i <= 2) {
                toAdd = 1;
                if (fib.get(i) != toAdd) {
                    System.out.println("Invalid: " + fib.get(i) + " != " + toAdd + " at pos " + i);
                    return false;
                }
            } else {
                toAdd = prev1 + prev2;
                if (fib.get(i) != (toAdd)) {
                    System.out.println("Invalid: " + fib.get(i) + " != " + toAdd + " at pos " + i);
                    return false;
                }
            }
            prev2 = prev1;
            prev1 = toAdd;
        }
        return true;
    }

    public static void test(final boolean useLock) throws InterruptedException {
        FibCalculator fib = new FibCalculator();
        List<Thread> threads = new ArrayList<>();
        ExecutorService exec = Executors.newFixedThreadPool(8, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                threads.add(t);
                return t;
            }
        });
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    List<Long> fibonacci;
                    if (useLock) {
                        fibonacci = fib.getFibonacci(rand.nextInt(10) + 10);
                    } else {
                        fibonacci = fib.getFibonacciNoLock(rand.nextInt(10) + 10);
                    }
                    Object print = fibonacci;
                    if (!validateFib(fibonacci)) {
                        print = "INVALID " + fibonacci ;

                    }
                    System.out.println(print + "  size " + fibonacci.size() );
                }
            });
        }
        exec.shutdown();
        StringBuilder lastPrint = new StringBuilder();

        while(!exec.isTerminated()) {
            StringBuilder sb = new StringBuilder();
            Thread.sleep(5);
            sb.append(exec);
            sb.append("\n");
            threads.forEach(thread -> {
                sb.append(thread.getName() +" "+ thread.getState() +" alive: " + thread.isAlive() + " interrupted: " + thread.isInterrupted());
                sb.append("\n");
            });
            if (!(sb.toString().equals(lastPrint.toString()))) {
                System.out.println(sb.toString());
                lastPrint = sb;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("lock:");
        test(true);
        System.out.println("no lock:");
        test(false);
    }

    /** Output:
     * using Lock: (only 1 task is running (RUNNABLE))
     * [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89]  size 12
     * java.util.concurrent.ThreadPoolExecutor@4b67cf4d[Shutting down, pool size = 8, active threads = 8, queued tasks = 1, completed tasks = 1]
     * Thread-0 WAITING alive: true interrupted: false
     * Thread-1 RUNNABLE alive: true interrupted: false
     * Thread-2 WAITING alive: true interrupted: false
     * Thread-3 WAITING alive: true interrupted: false
     * Thread-4 WAITING alive: true interrupted: false
     * Thread-5 WAITING alive: true interrupted: false
     * Thread-6 WAITING alive: true interrupted: false
     * Thread-7 WAITING alive: true interrupted: false
     *
     * not using Lock:  (all tasks are running at same time and ruin fibonacci sequence)
     * Invalid: 0 != 2 at pos 3
     * INVALID [0, 1, 1, 0, 0, 1, 0, 1, 2, 1, 0, 0]  size 12
     * java.util.concurrent.ThreadPoolExecutor@4f3f5b24[Shutting down, pool size = 8, active threads = 8, queued tasks = 1, completed tasks = 1]
     * Thread-8 RUNNABLE alive: true interrupted: false
     * Thread-9 RUNNABLE alive: true interrupted: false
     * Thread-10 RUNNABLE alive: true interrupted: false
     * Thread-11 RUNNABLE alive: true interrupted: false
     * Thread-12 RUNNABLE alive: true interrupted: false
     * Thread-13 RUNNABLE alive: true interrupted: false
     * Thread-14 RUNNABLE alive: true interrupted: false
     * Thread-15 RUNNABLE alive: true interrupted: false
     */
}
