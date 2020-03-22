package roksard.concurrency.synchronization.sync_vs_lock.sync;

public class Checker implements Runnable {
    private final Pojo pojo;

    public Checker(Pojo pojo) {
        this.pojo = pojo;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (this) {
                int value = pojo.getValue();
                if (value % 5 != 0) {
                    throw new RuntimeException(value + " can not be divided by 5");
                }
            }
        }
    }
}
