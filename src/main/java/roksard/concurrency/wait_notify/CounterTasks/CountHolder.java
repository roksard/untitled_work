package roksard.concurrency.wait_notify.CounterTasks;

public class CountHolder {
    private Integer value;
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public CountHolder(Integer value, State state) {
        this.value = value;
        this.state = state;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
