package roksard.concurrency.interrupt;

public class TaskWithSleep implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                System.out.print("1/5 ");
                Thread.sleep(1);
                System.out.print("2/5 ");
                Thread.sleep(1);
                System.out.print("3/5 ");
                Thread.sleep(1);
                System.out.print("4/5 ");
                Thread.sleep(1);
                System.out.print("5/5 ");
            }
        } catch (InterruptedException e) {
            System.out.println();
            System.out.println(e);
        }
    }
}
