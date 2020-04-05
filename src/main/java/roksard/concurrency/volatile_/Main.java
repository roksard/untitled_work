package roksard.concurrency.volatile_;

import roksard.util.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static String someStaticObject = "";
    public static void log(Object obj) {
        System.out.println(Logger.timestamp() + " " + Thread.currentThread() + " " + obj);
    }

    public static void main(String[] args) throws InterruptedException {
        new Volatile().test(3);
        new NoVolatileSyncOnOtherCommonObject().test(3);
        new NoVolatileSyncOnOtherObject().test(3);
        new NoVolatileMethod().test(3);
        new NoVolatile().test(3);
    }
}

/* Output:
21:21:13.927 Thread[pool-2-thread-15,5,main] is started.
21:21:13.927 Thread[pool-2-thread-18,5,main] is started.
21:21:15.509 Thread[pool-2-thread-21,5,main] sending stop.
21:21:17.352 Thread[main,5,main] threads stil running.. shutting down app with code 1
//threads dont see that variable has been changed by another thread so they keep running forever (unless the variable is volatile)
 */