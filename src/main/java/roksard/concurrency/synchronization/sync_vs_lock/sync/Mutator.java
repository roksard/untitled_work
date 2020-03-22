package roksard.concurrency.synchronization.sync_vs_lock.sync;

public class Mutator implements Runnable {
    private final Pojo pojo;
    public Mutator(Pojo pojo) {
        this.pojo = pojo;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (pojo) {
                pojo.setValue(pojo.getValue() + 1);
                pojo.setValue(pojo.getValue() + 1);
                pojo.setValue(pojo.getValue() + 1);
                pojo.setValue(pojo.getValue() + 1);
                pojo.setValue(pojo.getValue() + 1);
            }
        }
    }
}
