package roksard.concurrency.wait_notify.waitOnObject;

public class WaitOnThisObject {
    void waitMethod() {
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void notifyMethod() {
        synchronized (this) {
            notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitOnThisObject obj = new WaitOnThisObject();

        System.out.println("starting new Thread and let it wait on obj");

        new Thread() {
            @Override
            public void run() {
                System.out.println("(from new Thread): waiting for obj notify");
                obj.waitMethod();
                System.out.println("(from new Thread): waiting finished");
            }
        }.start();

        System.out.println("sleeping 2 sec");
        Thread.sleep(2000);
        System.out.println("notify");
        obj.notifyMethod();
    }
}
