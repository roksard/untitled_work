package roksard.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Util {
    public static void sleepn(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String timestamp() {
        Instant time = Instant.now();
        return DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(ZonedDateTime.ofInstant(time,
                ZoneId.of("UTC+5")));
    }

    public void log(Object... toPrint) {
        System.out.println(timestamp() + Arrays.stream(toPrint)
                        .map(Object::toString)
                        .reduce("", (a, b) -> a + " " + b));
    }
}
