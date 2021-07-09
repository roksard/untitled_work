package roksard.algorithms.dynamicProgramming;

// Racoon tries to steal the best priced items in summary,
// but he has a limited backpack of 4 slots
// whats the best (highest price sum) combination of items for a backpack of size 4?
public class Main {
    public static void main(String[] args) {
        ItemSheet itemSheet = new ItemSheet(4);
        itemSheet.getItemList().add(new Item("iphone", 1000, 1));
        itemSheet.getItemList().add(new Item("laptop", 800, 2));
        itemSheet.getItemList().add(new Item("watch", 700, 1));
        itemSheet.getItemList().add(new Item("headphone", 400, 1));
        itemSheet.getItemList().add(new Item("stereo", 2000, 3));
        itemSheet.getItemList().add(new Item("bicycle", 2600, 4));

        ItemSheet calculated = DynamicProgramming.calculate(itemSheet);
        System.out.println("calculated: " + calculated);
    }
}
