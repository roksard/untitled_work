package roksard.incubator_simulator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        HumanGenerator hg = new HumanGenerator(exec);
        hg.createHuman();
        hg.createHuman();
        Thread.sleep(15000);
        exec.shutdownNow();
    }
}
