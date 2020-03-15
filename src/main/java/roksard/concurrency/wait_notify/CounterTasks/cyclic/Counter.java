package roksard.concurrency.wait_notify.CounterTasks.cyclic;

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
                while (true) {
                    if (holder.getState().equals(forState)) {
                        Thread.sleep(500);
                        holder.setValue(holder.getValue() + 1);
                        System.out.println(forState + ": " + holder.getValue());
                        holder.setState(State.values()[(forState.ordinal() + 1) % State.values().length]);
                        holder.notifyAll();
                    }
                    holder.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
