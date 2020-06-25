package roksard.util;

import java.io.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Logger {
    private String prefix;
    private boolean isDisabled = false;
    private String fileName;

    public static Logger getLogger(String prefix) {
        return new Logger(prefix);
    }

    public static Logger getLogger(String fileName, String prefix) {
        return new Logger(fileName, prefix);
    }

    private Logger(String prefix) {
        this.prefix = prefix;
    }

    private Logger(String fileName, String prefix) {
        this.prefix = prefix;
        this.fileName = fileName;
    }

    public static String timestamp() {
        Instant time = Instant.now();
        return DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(ZonedDateTime.ofInstant(time,
                ZoneId.systemDefault()));
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public void log(Object... toPrint) {
        FileOutputStream fs = null;
        PrintStream ps = null;
        PrintStream printStream = System.out;
        try {
            if (fileName != null) {
                try {
                    fs = new FileOutputStream(fileName, false);
                    ps = new PrintStream(fs);
                    printStream = ps;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Could not create print stream!");
                }
            }
            if (!isDisabled) {
                printStream.println(timestamp() + " --" + prefix + "-- " + Arrays.stream(toPrint)
                        .map(Object::toString)
                        .reduce("", (a, b) -> a + " " + b));
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
