package roksard.algorithms.dynamicProgramming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class ItemSheet {
    public ItemSheet(Integer backpackSize) {
        this.backpackSize = backpackSize;
    }

    List<Item> itemList = new ArrayList<>();
    Integer backpackSize;
    Map<Integer, List<Item>> bestCombo = new HashMap<>();
}
