package roksard.words_learning_model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class WordCard {
    private int wordCount = 10;
    private Instant nextRepeat;
    private static Comparator<WordCard> comparator = new Comparator<WordCard>() {
        @Override
        public int compare(WordCard a, WordCard b) {
            boolean aReady = a.getLearnProgress() >= a.getTimeToLearn();
            boolean bReady = b.getLearnProgress() >= b.getTimeToLearn();
            if (aReady && !bReady) {
                return 1;
            } else if (!aReady && bReady) {
                return -1;
            } else {
                if (a.getNextRepeat() == null && b.getNextRepeat() != null) {
                    return 1;
                } else if (a.getNextRepeat() != null && b.getNextRepeat() == null) {
                    return -1;
                } else if (a.getNextRepeat() == null && b.getNextRepeat() == null) {
                    return 0;
                } else {
                    return a.getNextRepeat().compareTo(b.getNextRepeat());
                }
            }
        }
    };


    private Instant prevRepeat;
    private Instant createdAt;
    private ChronoUnit timeToLearnUnit = ChronoUnit.DAYS;
    private int timeToLearn = 2;
    private int learnProgress;

    public Instant getPrevRepeat() {
        return prevRepeat;
    }

    public void setPrevRepeat(Instant prevRepeat) {
        this.prevRepeat = prevRepeat;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public Instant getNextRepeat() {
        return nextRepeat;
    }

    public void setNextRepeat(Instant nextRepeat) {
        this.nextRepeat = nextRepeat;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public ChronoUnit getTimeToLearnUnit() {
        return timeToLearnUnit;
    }

    public void setTimeToLearnUnit(ChronoUnit timeToLearnUnit) {
        this.timeToLearnUnit = timeToLearnUnit;
    }

    public int getTimeToLearn() {
        return timeToLearn;
    }

    public void setTimeToLearn(int timeToLearn) {
        this.timeToLearn = timeToLearn;
    }

    public int getLearnProgress() {
        return learnProgress;
    }

    public void setLearnProgress(int learnProgress) {
        this.learnProgress = learnProgress;
    }

    public WordCard(Instant createdAt, Instant nextRepeat) {
        this.nextRepeat = nextRepeat;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "{"+ LocalDateTime.ofInstant(createdAt, ZoneId.of("UTC")).toLocalDate().toString()
                + ", " + learnProgress + "/" + timeToLearn + ", "
                + ((nextRepeat == null) ? "-" : LocalDateTime.ofInstant(nextRepeat, ZoneId.of("UTC")).toLocalDate().toString())
                + "}";
    }


    public static Comparator<WordCard> getComparator() {
        return comparator;
    }
}
