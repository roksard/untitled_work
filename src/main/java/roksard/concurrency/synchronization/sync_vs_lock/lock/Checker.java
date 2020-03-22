package roksard.concurrency.synchronization.sync_vs_lock.lock;

public class Checker implements Runnable {
    private final Pojo pojo;

    public Checker(Pojo pojo) {
        this.pojo = pojo;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            pojo.getLock().lock();
            try {
                int value = pojo.value;
                if (value % 5 != 0) {
                    throw new RuntimeException(value + " can not be divided by 5");
                }
            } finally {
                pojo.getLock().unlock();
            }
        }
    }
}
