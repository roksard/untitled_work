package roksard.concurrency.synchronization.sync_vs_lock.lock;

import java.util.concurrent.locks.ReentrantLock;

public class Pojo {
    int value;
    final ReentrantLock lock = new ReentrantLock();

    public ReentrantLock getLock() {
        return lock;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value % 100000000;
    }
}
