package roksard.words_learning_model;

import java.time.temporal.ChronoUnit;

public abstract class Config {
    public final static ChronoUnit totalLearningPeriodUnit = ChronoUnit.DAYS;
    public final static int totalLearningPeriod = 365; //Set period of whole learning process for all cards
    public final static ChronoUnit cardFullLearningPeriodUnit = ChronoUnit.DAYS;
    public final static int cardFullLearningPeriod = 60; //learning of 1 card, when card is no longer to be repeated
    public final static int cardLimitPerDay = 2;
}
