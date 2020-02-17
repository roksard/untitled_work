package roksard.words_learning_model;

import javafx.collections.transformation.SortedList;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.*;

public class Main {
    public static void print(Instant timestamp, List<WordCard> wordCards) {
        System.out.println("Day: " + LocalDateTime.ofInstant(timestamp, ZoneId.of("UTC")).toLocalDate()
                + " words: " + wordCards);
    }

    public static String instantToDateString (List<Instant> instants) {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        instants.forEach(instant -> {
            sj.add(LocalDateTime.ofInstant(instant, ZoneId.of("UTC")).toLocalDate().toString());
        });
        return sj.toString();
    }

    public static long calcMaxRepeatExpirationDays(long maxRepeatExpirationDays, WordCard card, Instant current, List<Instant> writeInstants) {
        long days = ChronoUnit.DAYS.between(
                card.getNextRepeat().truncatedTo(ChronoUnit.DAYS),
                current.truncatedTo(ChronoUnit.DAYS));
        if (days > maxRepeatExpirationDays) {
            writeInstants.clear();
            writeInstants.add(Instant.from(current));
            writeInstants.add(card.getCreatedAt());
            return days;
        }
        return maxRepeatExpirationDays;
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        Instant current = start;
        Instant end = start.plus(Config.totalLearningPeriod, Config.totalLearningPeriodUnit);
        List<WordCard> wordCards = new ArrayList<>();
        long maxRepeatExpirationDays = 0;
        List<Instant> expiredTimestamps = new ArrayList<>();
        while (current.isBefore(end)) {
            print(current, wordCards);
            int cardsLearned = 0;
            for (WordCard card : wordCards) {
                if (card.getNextRepeat() != null && current.truncatedTo(ChronoUnit.DAYS).compareTo(
                        card.getNextRepeat().truncatedTo(ChronoUnit.DAYS)) >= 0
                ) {
                    maxRepeatExpirationDays = calcMaxRepeatExpirationDays(maxRepeatExpirationDays, card, current, expiredTimestamps);
                    if (card.getLearnProgress() < card.getTimeToLearn()) {
                        card.setPrevRepeat(current);
                        card.setNextRepeat(current.plus(1, ChronoUnit.DAYS));
                        card.setLearnProgress(card.getLearnProgress() + 1);
                    } else {
                        if (Config.cardFullLearningPeriodUnit.between(
                                card.getCreatedAt().truncatedTo(Config.cardFullLearningPeriodUnit),
                                current.truncatedTo(Config.cardFullLearningPeriodUnit)) > Config.cardFullLearningPeriod
                        ) {
                            card.setNextRepeat(null);
                        } else {
                            card.setNextRepeat(current.plus(
                                    ChronoUnit.DAYS.between(
                                            card.getPrevRepeat().truncatedTo(ChronoUnit.DAYS),
                                            card.getNextRepeat().truncatedTo(ChronoUnit.DAYS)) * 2,
                                    ChronoUnit.DAYS
                            ));
                        }
                        card.setPrevRepeat(current);
                    }
                    cardsLearned++;
                    if (cardsLearned >= Config.cardLimitPerDay) {
                        break;
                    }
                }
            }
            if (cardsLearned < Config.cardLimitPerDay) {
                WordCard wordCard = new WordCard(current, current.plus(1, ChronoUnit.DAYS));
                wordCard.setLearnProgress(wordCard.getLearnProgress() + 1);
                wordCards.add(wordCard);
                cardsLearned++;
            }
            print(current, wordCards);
            wordCards.sort(WordCard.getComparator());
            //Util.sleep(1000);
            current = current.plus(1, ChronoUnit.DAYS);
        }
        System.out.println("Words learned in " + Config.totalLearningPeriod + " "
                + Config.totalLearningPeriodUnit
                + ": " + wordCards.stream().map(WordCard::getWordCount).reduce(Integer::sum).orElse(0)
        );
        System.out.println("maxRepeatExpirationDays: " + maxRepeatExpirationDays + " at card "
                + instantToDateString(expiredTimestamps));
    }
}
