package roksard.concurrency.interrupt;

public class TaskNoSleepCheckThread implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("1/5 ");
            System.out.print("2/5 ");
            System.out.print("3/5 ");
            System.out.print("4/5 ");
            System.out.print("5/5 ");
        }
        System.out.println("\nexiting " + this + " because thread is interrupted");
    }
}
