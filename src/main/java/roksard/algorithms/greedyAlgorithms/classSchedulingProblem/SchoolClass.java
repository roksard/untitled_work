package roksard.algorithms.greedyAlgorithms.classSchedulingProblem;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.plugin.dom.exception.InvalidStateException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Getter
@Setter
public class SchoolClass {
    static Random rand = new Random();
    Instant starts;
    Instant ends;
    static Logger logger = LoggerFactory.getLogger(SchoolClass.class);


    public long getDurationMinutes() {
        return ChronoUnit.MINUTES.between(starts, ends);
    }

    private SchoolClass() {
    }

    public static class Builder {
        Instant starts;
        Instant ends;
        public SchoolClass build() {
            if (starts != null && ends != null) {
                SchoolClass aClass = new SchoolClass();
                aClass.setEnds(ends);
                aClass.setStarts(starts);
                return aClass;
            }
            throw new InvalidStateException("not all fields are filled");
        }

        public Builder starts(Instant starts) {
            this.starts = starts;
            return this;
        }

        public Builder ends(Instant ends) {
            this.ends = ends;
            return this;
        }

    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "starts=" + starts +
                ", ends=" + ends +
                '}';
    }

    static void getInstanceRandom() {
        Instant start = Instant.now().plus(rand.nextInt(10), ChronoUnit.MINUTES);
        SchoolClass aClass = new SchoolClass.Builder()
                .starts(start)
                .ends(start.plus(rand.nextInt(40)+5, ChronoUnit.MINUTES))
                .build();

        logger.debug(aClass.toString());
        logger.debug("duration: " + aClass.getDurationMinutes());
    }
}
