package roksard.date_periods;

import java.time.LocalDate;

public class DatePeriod {
    private LocalDate start;
    private LocalDate finish;

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getFinish() {
        return finish;
    }

    public void setFinish(LocalDate finish) {
        this.finish = finish;
    }
}
