package roksard.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormat1 {
    public static void main(String[] args) {
        Date date = Calendar.getInstance().getTime();
        System.out.println(
                new SimpleDateFormat("dd.MM.yyyy").format(date)     );
        System.out.println("test");
        System.out.println(
                new SimpleDateFormat("dd.MM.yyyy").format(null)     );
    }
}
