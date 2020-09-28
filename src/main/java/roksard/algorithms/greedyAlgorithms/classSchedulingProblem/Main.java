package roksard.algorithms.greedyAlgorithms.classSchedulingProblem;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void printList(String name, List<?> list) {
        StringBuilder sb = new StringBuilder(name + " [\n");
        for (Object o : list) {
            sb.append("  ").append(o.toString()).append("\n");
        }
        sb.append("]\n");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        List<SchoolClass> allClasses = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            allClasses.add(SchoolClass.getInstanceRandom());
        }

        List<SchoolClass> choosenClasses = new ArrayList<>();
        SchoolClass minEnds = null;
        Instant prevEnds = null;
        do {
            minEnds = null;
            for (SchoolClass c : allClasses) {
                if (prevEnds == null || prevEnds.isBefore(c.starts)) {
                    if (minEnds == null || minEnds.ends.isAfter(c.ends)) {
                        minEnds = c;
                    }
                }
            }
            if (minEnds != null) {
                prevEnds = minEnds.ends;
                choosenClasses.add(minEnds);
            }
        } while (minEnds != null);

        printList("allClasses", allClasses);
        printList("choosenClasses", choosenClasses);
    }
}
