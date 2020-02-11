package roksard.datetime;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class TimeZone_ {
    public static void main(String[] args) {
        Instant inst = Instant.now();
        ZonedDateTime zonedDateTime = inst.atZone(ZoneId.of("Asia/Yekaterinburg"));
        Timestamp.valueOf(ZonedDateTime.now(ZoneId.of("Asia/Yekaterinburg")).toLocalDateTime());
        System.out.println(Arrays.toString(TimeZone.getAvailableIDs()));
    }
}
