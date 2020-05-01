package roksard.Random;

import java.util.Random;

public class RandomNumberGenerator {
    public static void main(String[] args) {
        int numbersCount = 50;
        int from = 10;
        int to = 1000;

        Random rand = new Random();
        for(int i = 0; i < numbersCount; i++) {
            int number = rand.nextInt(to - from + 1) + from;
            System.out.print(number + ", ");
        }

    }
}
