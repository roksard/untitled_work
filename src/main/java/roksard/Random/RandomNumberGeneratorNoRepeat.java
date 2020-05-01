package roksard.Random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNumberGeneratorNoRepeat {
    public static void main(String[] args) {
        int from = 0;
        int to = 100;
        int numbersCount = to-from+1;

        Random rand = new Random();
        List<Integer> list = new ArrayList<>(numbersCount);
        for(int i = from; i < numbersCount; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        System.out.println(list);
    }
}
