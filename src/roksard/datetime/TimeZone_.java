package roksard.datetime;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.TimeZone;

public class TimeZone_ {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(TimeZone.getAvailableIDs()));
//        Clock defaultClock = Clock.system(ZoneId.of("GMT+1"));
//        Instant inst = Instant.now(defaultClock);
//        Timestamp t1 = new Timestamp(inst.toEpochMilli());
        Timestamp t2 = Timestamp.valueOf(ZonedDateTime.now(ZoneId.of("Asia/Yekaterinburg")).toLocalDateTime());
        //System.out.println(t1);
        System.out.println(t2);
    }
}
