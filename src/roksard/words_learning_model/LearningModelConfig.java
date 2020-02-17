package roksard.words_learning_model;

import java.time.temporal.ChronoUnit;

public class LearningModelConfig {
    public final ChronoUnit totalLearningPeriodUnit;
    public final int totalLearningPeriod; //Set period of whole learning process for all cards
    public final ChronoUnit cardFullLearningPeriodUnit;
    public final int cardFullLearningPeriod; //learning of 1 card, when card is no longer to be repeated
    public final int cardLimitPerDay;

    public LearningModelConfig() {
        this.totalLearningPeriodUnit = ChronoUnit.DAYS;
        this.totalLearningPeriod = 365;
        this.cardFullLearningPeriodUnit = ChronoUnit.DAYS;
        this.cardFullLearningPeriod = 60;
        this.cardLimitPerDay = 2;
    }

    public LearningModelConfig(ChronoUnit totalLearningPeriodUnit, int totalLearningPeriod, ChronoUnit cardFullLearningPeriodUnit, int cardFullLearningPeriod, int cardLimitPerDay) {
        this.totalLearningPeriodUnit = totalLearningPeriodUnit;
        this.totalLearningPeriod = totalLearningPeriod;
        this.cardFullLearningPeriodUnit = cardFullLearningPeriodUnit;
        this.cardFullLearningPeriod = cardFullLearningPeriod;
        this.cardLimitPerDay = cardLimitPerDay;
    }
}
