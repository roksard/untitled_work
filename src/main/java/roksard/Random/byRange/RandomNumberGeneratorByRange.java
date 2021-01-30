package roksard.Random.byRange;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomNumberGeneratorByRange {
    Random random = new Random();
    List<Range> rangeList;

    public RandomNumberGeneratorByRange(List<Range> rangeList) {
        this.rangeList = rangeList;
    }

    int randomRange(int from, int to) {
        return random.nextInt(to - from + 1) + from;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        outer:
        while (true) {
            for(Range range : rangeList) {
                if (random.nextInt(100)+1 <= range.getPercentProbability()) {
                    System.out.println(randomRange(range.getFrom(), range.getTo()));
                    System.out.println("press enter to continue or q to exit");
                    if ("q".equals(scanner.nextLine())) {
                        break outer;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Range> rangeList = new ArrayList<>();
        rangeList.add(new Range(0, 99, 30));
        rangeList.add(new Range(100, 999, 30));
        rangeList.add(new Range(1000, 99999, 30));
        rangeList.add(new Range(10000000, 99999999, 30));

        RandomNumberGeneratorByRange generator = new RandomNumberGeneratorByRange(rangeList);
        generator.start();
    }
}
