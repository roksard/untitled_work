package roksard.words_learning_model;

import javafx.collections.transformation.SortedList;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Instant start = Instant.now();
        Instant current = start;
        Instant end = start.plus(Config.totalLearningPeriod, Config.totalLearningPeriodUnit);
        List<WordCard> wordCards = new ArrayList<>();
        while (current.isBefore(end)) {
            boolean todayIsBusy = false;
            for (WordCard card : wordCards) {
                if (current.truncatedTo(ChronoUnit.DAYS).compareTo(
                        card.getNextRepeat().truncatedTo(ChronoUnit.DAYS)) >= 0) {
                    if (card.getLearnProgress() < card.getTimeToLearn()) {
                        card.setPrevRepeat(current);
                        card.setNextRepeat(current.plus(1, ChronoUnit.DAYS));
                        card.setLearnProgress(card.getLearnProgress() + 1);
                    } else {
                        card.setNextRepeat(current.plus(
                                ChronoUnit.DAYS.between(
                                        card.getPrevRepeat().truncatedTo(ChronoUnit.DAYS),
                                        card.getNextRepeat().truncatedTo(ChronoUnit.DAYS)) * 2,
                                ChronoUnit.DAYS
                        ));
                        card.setPrevRepeat(current);
                    }
                    todayIsBusy = true;
                    break;
                }
            }
            if (!todayIsBusy) {
                WordCard wordCard = new WordCard(current, current.plus(1, ChronoUnit.DAYS));
                wordCard.setLearnProgress(wordCard.getLearnProgress() + 1);
                wordCards.add(wordCard);
                todayIsBusy = true;
            }
            wordCards.sort(WordCard.getComparator());
            System.out.println("Day: " + LocalDateTime.ofInstant(current, ZoneId.of("UTC")).toLocalDate()
                    + " words: " + wordCards);
            //Util.sleep(1000);
            current = current.plus(1, ChronoUnit.DAYS);
        }
        System.out.println("Words learned in " + Config.totalLearningPeriod + " "
                + Config.totalLearningPeriodUnit
                + ": " + wordCards.stream().map(WordCard::getWordCount).reduce(Integer::sum).orElse(0)
                + "\n cards: " + wordCards.size()
        );
    }
}
