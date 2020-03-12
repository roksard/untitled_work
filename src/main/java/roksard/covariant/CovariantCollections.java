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
        System.out.println("\nArrayList<Mango>");
        Object mangoList = new ArrayList<Mango>();
        ((ArrayList)mangoList).add(new Mango());
        System.out.println(mangoList);
        ((ArrayList)mangoList).add(new Banana());
        System.out.println(mangoList);
        ((ArrayList)mangoList).add(new Fruit());
        System.out.println(mangoList);


        //covariant types:
        ArrayList<? extends Fruit> exFruit = new ArrayList<>();  //for reading only
        Fruit fruit = exFruit.get(0);
        Fruit mango = new Mango();
        //exFruit.add(fruit); //cant add anything
        //exFruit.add(mango);

        //covariant types:
        ArrayList<? super Mango> superMango = new ArrayList<>();
        Object object = superMango.get(0); //reading as Object
        superMango.add(new Mango()); //writing only exact type
        //superMango.add(new Fruit()); //can't write


    }
}

/* Output:
ArrayList<Fruit>
[Fruit]
[Fruit, Banana]
[Fruit, Banana, Mango]

ArrayList<Mango>
[Mango]
[Mango, Banana]  //!! Mango generic type information erased

[Mango, Banana, Fruit] //!!


Process finished with exit code 0

 */
