package roksard.concurrency.synchronization.sync_vs_lock.lock;

public class Mutator implements Runnable {
    private final Pojo pojo;
    public Mutator(Pojo pojo) {
        this.pojo = pojo;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            pojo.getLock().lock();
            try {
                pojo.value++;
                pojo.value++;
                pojo.value++;
                pojo.value++;
                pojo.value++;
            } finally {
                pojo.getLock().unlock();
            }
        }
    }
}
