package roksard.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Calendar1 {
    public static void main(String[] args) {
        DateFormat ff = SimpleDateFormat.getDateTimeInstance();

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 12; i++) {
            calendar.add(Calendar.MONTH, 1);
            System.out.println(ff.format(calendar.getTime()));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            System.out.println(ff.format(calendar.getTime()));
            System.out.println();
        }
    }

}
