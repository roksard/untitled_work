package roksard.concurrency.syncOnLocalObjectWarning;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainSyncOnLocalWarning {

    static void singeThreadRun() {
        //single thread run
        NaturalNumbersGenerator generator = new NaturalNumbersGenerator();

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Runnable runnable1 = new RunnableWName(generator, "/A", false);
        Runnable runnable2 = new RunnableWName(generator, "/B", false);
        Runnable runnable3 = new RunnableWName(generator, "/C", false);
        executorService.submit(runnable1);
        executorService.submit(runnable2);
        executorService.submit(runnable3);
        executorService.shutdown();

        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("singleThreadRun: " + generator.getListNaturalNumbers());
    }

    static void multiThreadRun() {
        //single thread run
        NaturalNumbersGenerator generator = new NaturalNumbersGenerator();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Runnable runnable1 = new RunnableWName(generator, "/A", false);
        Runnable runnable2 = new RunnableWName(generator, "/B", false);
        Runnable runnable3 = new RunnableWName(generator, "/C", false);
        executorService.submit(runnable1);
        executorService.submit(runnable2);
        executorService.submit(runnable3);
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("multiThreadRun: " + generator.getListNaturalNumbers());
    }

    static void multiThreadRunWithSync() {
        //single thread run
        NaturalNumbersGenerator generator = new NaturalNumbersGenerator();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Runnable runnable1 = new RunnableWName(generator, "/A", true); //true = using synchronization
        Runnable runnable2 = new RunnableWName(generator, "/B", true);
        Runnable runnable3 = new RunnableWName(generator, "/C", true);
        executorService.submit(runnable1);
        executorService.submit(runnable2);
        executorService.submit(runnable3);
        executorService.shutdown();

        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("multiThreadRunWithSync: " + generator.getListNaturalNumbers());
    }

    public static void main(String[] args) {
        singeThreadRun();
        multiThreadRun();
        multiThreadRunWithSync();
    }

}
