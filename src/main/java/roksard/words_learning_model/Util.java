package roksard.words_learning_model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Util {
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
