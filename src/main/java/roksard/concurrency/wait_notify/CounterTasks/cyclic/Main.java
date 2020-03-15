package roksard.concurrency.wait_notify.CounterTasks.cyclic;

import roksard.concurrency.wait_notify.CounterTasks.CountHolder;
import roksard.concurrency.wait_notify.CounterTasks.State;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountHolder countHolder = new CountHolder(0, State.A);
        exec.execute(new Counter(countHolder, State.B));
        exec.execute(new Counter(countHolder, State.A));
        exec.execute(new Counter(countHolder, State.C));
        Thread.sleep(5000);
        exec.shutdownNow();
    }
}
