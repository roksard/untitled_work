package roksard.algorithms.dynamicProgramming.backpack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicProgramming {
    static Integer sumPrise(List<Item> items) {
        if (items == null) {
            return 0;
        }
        return items.stream()
                .map(Item::getPrice)
                .reduce(Integer::sum)
                .orElse(0);
    }

    static ItemSheet calculate(ItemSheet itemSheet) {
        Map<Integer, List<Item>> bestCombo = itemSheet.getBestCombo();
        for(Item item : itemSheet.getItemList()) {
            //new bestCombo during being calculated at this step should not affect our current bestCombo, thats why we forming it in another variable and later applying as a new bestCombo
            Map<Integer, List<Item>> bestComboNew = new HashMap<>(bestCombo);
            for (int i = 1; i <= itemSheet.getBackpackSize(); i++) {
                //for each item we recalculating if its a better choice to make a combo with this new item, comparing to previous bestCombo, i is current sub-backpackSize
                //always recalculate all sub-backbacks for every amounts of slots
                int restSize = Math.max(i - item.getSize(), 0);
                if (item.getSize() <= i
                        && sumPrise(bestCombo.get(i)) <= (item.getPrice() + sumPrise(bestCombo.get(restSize)))
                ) {
                    List<Item> newItems = new ArrayList<>();
                    newItems.add(item);
                    if (bestCombo.get(restSize) != null) {
                        newItems.addAll(bestCombo.get(restSize));
                    }
                    bestComboNew.put(i, newItems);
                }
            }
            bestCombo = bestComboNew;
        }
        itemSheet.setBestCombo(bestCombo);
        //debug info
        for (int i = 1; i <= itemSheet.getBackpackSize(); i++) {
            System.out.printf("%d : %d : %s\n", i, sumPrise(bestCombo.get(i)), bestCombo.get(i));
        }

        return itemSheet;
    }
}
