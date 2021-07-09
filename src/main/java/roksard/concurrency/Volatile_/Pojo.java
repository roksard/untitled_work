package roksard.concurrency.Volatile_;

public class Pojo {
    boolean stop = false;
    volatile boolean stopVolatile = false;

    public boolean getStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
