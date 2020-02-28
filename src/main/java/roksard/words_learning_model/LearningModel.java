package roksard.words_learning_model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class LearningModel {
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

    public static void start(LearningModelConfig config, boolean printIntermediate) {
        Instant start = Instant.now();
        Instant current = start;
        Instant end = start.plus(config.totalLearningPeriod, config.totalLearningPeriodUnit);
        List<WordCard> wordCards = new ArrayList<>();
        long maxRepeatExpirationDays = 0;
        List<Instant> expiredTimestamps = new ArrayList<>();
        while (current.isBefore(end)) {
            if (printIntermediate) {
                print(current, wordCards);
            }
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
                        if (config.cardFullLearningPeriodUnit.between(
                                card.getCreatedAt().truncatedTo(config.cardFullLearningPeriodUnit),
                                current.truncatedTo(config.cardFullLearningPeriodUnit)) > config.cardFullLearningPeriod
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
                    if (cardsLearned >= config.cardLimitPerDay) {
                        break;
                    }
                }
            }
            if (cardsLearned < config.cardLimitPerDay) {
                WordCard wordCard = new WordCard(current, current.plus(1, ChronoUnit.DAYS));
                wordCard.setLearnProgress(wordCard.getLearnProgress() + 1);
                wordCards.add(wordCard);
                cardsLearned++;
            }
            if (printIntermediate) {
                print(current, wordCards);
            }
            wordCards.sort(WordCard.getComparator());
            current = current.plus(1, ChronoUnit.DAYS);
        }
        long totalWords = wordCards.stream().map(WordCard::getWordCount).reduce(Integer::sum).orElse(0);
        System.out.println("Words learned in " + config.totalLearningPeriod + " "
                + config.totalLearningPeriodUnit
                + ": " + totalWords + " (" + String.format("%.2f", (double)totalWords/config.totalLearningPeriod)  +  "/day) "
        );
        System.out.println("maxRepeatExpirationDays: " + maxRepeatExpirationDays + " at card "
                + instantToDateString(expiredTimestamps));
    }
}
