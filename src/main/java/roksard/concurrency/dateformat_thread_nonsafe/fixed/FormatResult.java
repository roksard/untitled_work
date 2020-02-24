package roksard.concurrency.dateformat_thread_nonsafe.fixed;

public class FormatResult {
    private String result;
    private long time;

    public FormatResult(String result, long time) {
        this.result = result;
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
