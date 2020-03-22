package roksard.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Logger {
    private String prefix;

    public static Logger getLogger(String prefix) {
        return new Logger(prefix);
    }

    private Logger(String prefix) {
        this.prefix = prefix;
    }

    public static String timestamp() {
        Instant time = Instant.now();
        return DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(ZonedDateTime.ofInstant(time,
                ZoneId.systemDefault()));
    }

    public void log(Object... toPrint) {
        System.out.println(timestamp() + " --" + prefix + "-- " + Arrays.stream(toPrint)
                .map(Object::toString)
                .reduce("", (a, b) -> a + " " + b));
    }
}
