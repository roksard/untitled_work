package roksard.concurrency.synchronization.sync_vs_lock.sync;

public class Pojo {
    int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value % 100000000;
    }
}
