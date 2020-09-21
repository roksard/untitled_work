package roksard.algorithms.greedyAlgorithms.classSchedulingProblem;

import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        SchoolClass aClass = new SchoolClass.Builder()
                .starts(Instant.now())
                .ends(Instant.now())
                .build();

        System.out.print("duration:");
        System.out.println(aClass.getDurationMinutes());
    }
}
