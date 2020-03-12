package roksard.covariant;

import java.util.ArrayList;

class Fruit {
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

class Banana extends Fruit {

}

class Mango extends Fruit {

}

public class CovariantCollections {
    public static void main(String[] args) {
        //Into Fruit list we can put anything
        System.out.println("ArrayList<Fruit>");
        Object fruitList = new ArrayList<Fruit>();
        ((ArrayList)fruitList).add(new Fruit());
        System.out.println(fruitList);
        ((ArrayList)fruitList).add(new Banana());
        System.out.println(fruitList);
        ((ArrayList)fruitList).add(new Mango());
        System.out.println(fruitList);

        //trying to put Banana into Mango-list
        System.out.println("ArrayList<Mango>");
        Object mangoList = new ArrayList<Mango>();
        ((ArrayList)mangoList).add(new Mango());
        System.out.println(mangoList);
        ((ArrayList)mangoList).add(new Banana());
        System.out.println(mangoList);
        ((ArrayList)mangoList).add(new Fruit());
        System.out.println(mangoList);
    }
}

/* Output:
[Fruit]
[Fruit, Banana]
[Fruit, Banana, Mango]
[Mango]
[Mango, Banana]
[Mango, Banana, Fruit]

Process finished with exit code 0

 */
