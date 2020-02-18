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
    }
}
