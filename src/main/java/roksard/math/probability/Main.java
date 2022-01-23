package roksard.math.probability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    static Random random = new Random();

    /**
     * shows a probability to get a number of successful hits
     * for exampe with successPercent = 90%, probability to get
     * @param args
     */
    public static void main(String[] args) {
        int successPercent = 95;
        int numberOfHits = 100;
        Map<Integer,Integer> customerNPerQuantity = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            int successCount = 0;
            for (int j = 0; j < numberOfHits; j++) {
                if (random.nextInt(100)+1 <= successPercent) {
                    successCount++;
                }
            }
            customerNPerQuantity.merge(successCount, 1, Integer::sum);
        }

        Integer sum = customerNPerQuantity.values().stream().reduce(Integer::sum).orElse(0);
        Map<Integer,String> customerNPerProcent = new HashMap<>();
        for (Map.Entry<Integer, Integer> customerNPerQuantityEntry : customerNPerQuantity.entrySet()) {
            customerNPerProcent.put(customerNPerQuantityEntry.getKey(), String.format("%.02f", customerNPerQuantityEntry.getValue() / (double)sum * 100));
        }

        System.out.println("Success probability = " + successPercent + "%");
        System.out.println("Number of total hits = " + numberOfHits);
        System.out.println("number of successful hits : probability to get this number (%). If a number is not shown, then its probability is 0%");
//        System.out.println(customerNPerQuantity);

        ArrayList<Integer> listOfKeys = new ArrayList<Integer>(customerNPerProcent.keySet());
        listOfKeys.sort(Integer::compareTo);
        for (Integer key : listOfKeys) {
            System.out.println(key + " : " + customerNPerProcent.get(key));
        }

    }
}
