package roksard.algorithms.dynamicProgramming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Item {
    String name;
    Integer price;
    Integer size;

    @Override
    public String toString() {
        return "Item{" +
                "name=" + name +
                ", price=" + price +
                ", size=" + size +
                '}';
    }
}
