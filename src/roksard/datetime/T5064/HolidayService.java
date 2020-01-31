package roksard.datetime.T5064;

import java.util.Calendar;
import java.util.Date;

public class HolidayService {
    public boolean isHoliday(Date date) {
        return false;
    }

    public Date addWorkDayToDate(Date date, Integer dayCount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        if (date != null) {
            for (int i = 0; i < dayCount; i++) {
                c.add(Calendar.DATE, 1);
                while (isHoliday(c.getTime())) {
                    c.add(Calendar.DATE, 1);
                }
            }
        }
        return c.getTime();
    }
}
