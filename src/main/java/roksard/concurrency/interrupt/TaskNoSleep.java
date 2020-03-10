package roksard.concurrency.interrupt;

public class TaskNoSleep implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.print("1/5 ");
            System.out.print("2/5 ");
            System.out.print("3/5 ");
            System.out.print("4/5 ");
            System.out.print("5/5 ");
        }
    }
}
