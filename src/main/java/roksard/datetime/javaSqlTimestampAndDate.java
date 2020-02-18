package roksard.datetime;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class javaSqlTimestampAndDate {
    public static Timestamp now() {
        return Timestamp.valueOf(ZonedDateTime.now(ZoneId.of("Asia/Yekaterinburg")).toLocalDateTime());
    }
    public static Date nowAsDate() {
        return new java.sql.Date(now().getTime());
    }


    public static void main(String[] args) {
        Timestamp ts = now();
        java.sql.Date date = nowAsDate();
        System.out.println(ts + " : \n  " + ts.getTime());
        System.out.println(date + " : \n  " + date.getTime());
    }
}
