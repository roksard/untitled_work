package roksard.words_learning_model;

import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        LearningModel.start(new LearningModelConfig(ChronoUnit.DAYS, 90, ChronoUnit.DAYS, 60, 1), false);
        LearningModel.start(new LearningModelConfig(ChronoUnit.DAYS, 180, ChronoUnit.DAYS, 60, 1), false);
        LearningModel.start(new LearningModelConfig(ChronoUnit.DAYS, 360, ChronoUnit.DAYS, 60, 1), false);
        LearningModel.start(new LearningModelConfig(ChronoUnit.DAYS, 720, ChronoUnit.DAYS, 60, 1), false);
        LearningModel.start(new LearningModelConfig(ChronoUnit.DAYS, 90, ChronoUnit.DAYS, 90, 1), false);
        LearningModel.start(new LearningModelConfig(ChronoUnit.DAYS, 180, ChronoUnit.DAYS, 90, 1), false);
        LearningModel.start(new LearningModelConfig(ChronoUnit.DAYS, 360, ChronoUnit.DAYS, 90, 1), false);
        LearningModel.start(new LearningModelConfig(ChronoUnit.DAYS, 720, ChronoUnit.DAYS, 90, 1), false);
        LearningModel.start(new LearningModelConfig(ChronoUnit.DAYS, 7200, ChronoUnit.DAYS, 90, 1), false);
        LearningModel.start(new LearningModelConfig(ChronoUnit.DAYS, 72000, ChronoUnit.DAYS, 90, 1), false);
    }
}

/* Output
Words learned in 90 Days: 140 (1,56/day)
maxRepeatExpirationDays: 3 at card [2020-05-09, 2020-04-19]
Words learned in 180 Days: 250 (1,39/day)
maxRepeatExpirationDays: 3 at card [2020-05-09, 2020-04-19]
Words learned in 360 Days: 470 (1,31/day)
maxRepeatExpirationDays: 3 at card [2020-05-09, 2020-04-19]
Words learned in 720 Days: 920 (1,28/day)
maxRepeatExpirationDays: 3 at card [2020-05-09, 2020-04-19]
Words learned in 90 Days: 140 (1,56/day)
maxRepeatExpirationDays: 3 at card [2020-05-09, 2020-04-19]
Words learned in 180 Days: 240 (1,33/day)
maxRepeatExpirationDays: 3 at card [2020-05-09, 2020-04-19]
Words learned in 360 Days: 440 (1,22/day)
maxRepeatExpirationDays: 4 at card [2020-08-26, 2020-04-12]
Words learned in 720 Days: 830 (1,15/day)
maxRepeatExpirationDays: 4 at card [2020-08-26, 2020-04-12]
Words learned in 7200 Days: 8040 (1,12/day)
maxRepeatExpirationDays: 5 at card [2029-12-18, 2029-10-02]
Words learned in 72000 Days: 80030 (1,11/day)
maxRepeatExpirationDays: 5 at card [2029-12-18, 2029-10-02]

Process finished with exit code 0

 */
