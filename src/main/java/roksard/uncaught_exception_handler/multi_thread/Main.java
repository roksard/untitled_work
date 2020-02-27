package roksard.uncaught_exception_handler.multi_thread;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class Main {
    public static void throwRuntimeException() {
        throw new RuntimeException("runtime");
    }
    public static void throwException() throws Exception {
        throw new Exception("simple");
    }
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println("\nuncaught at " + t + " :: " + e +  "::" +
                                String.join(", \n", Arrays
                                        .stream(e.getStackTrace())
                                        .map(elem -> elem.toString())
                                        .collect(Collectors.toList())));
                    }
                });
                return t;
            }
        });
        for (int i = 0; i < 10; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    throwRuntimeException();
                }
            });
        }
        exec.shutdown();
        throwRuntimeException();
    }
}
