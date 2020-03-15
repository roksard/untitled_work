package roksard.concurrency.wait_notify.CounterTasks.noncycle;

import roksard.concurrency.wait_notify.CounterTasks.CountHolder;
import roksard.concurrency.wait_notify.CounterTasks.State;

public class Counter implements Runnable {
    private CountHolder holder;
    private State forState;

    public Counter(CountHolder holder, State forState) {
        this.holder = holder;
        this.forState = forState;
    }

    @Override
    public void run() {
        try {
            synchronized (holder) {
                while (!holder.getState().equals(forState)) {
                    holder.wait();
                }
                Thread.sleep(500);
                holder.setValue(holder.getValue() + 1);
                System.out.println(forState + ": " + holder.getValue());
                holder.setState(State.values()[(forState.ordinal() + 1) % State.values().length]);
                holder.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
