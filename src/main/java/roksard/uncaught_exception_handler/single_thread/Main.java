package roksard.uncaught_exception_handler.single_thread;

import java.util.Arrays;

public class Main {
    public static void methodA() {
        throw new RuntimeException("eloelo");
    }
    public static void main(String[] args) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("uncaught at " + t + " :: " + e +  "::" + Arrays.toString(e.getStackTrace()));
            }
        });
//        methodA();
//        methodA();
        throw new Exception("standard non-runtime exception");
    }
}
