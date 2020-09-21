package roksard.algorithms.greedyAlgorithms.classSchedulingProblem;

import lombok.Getter;
import lombok.Setter;
import sun.plugin.dom.exception.InvalidStateException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class SchoolClass {
    Instant starts;
    Instant ends;


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
}
