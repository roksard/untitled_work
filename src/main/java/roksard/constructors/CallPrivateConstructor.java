package roksard.constructors;

public class CallPrivateConstructor {
    private CallPrivateConstructor() {
        System.out.println("Hello from private constructor");
        //throw new AssertionError("class is not instantiable"); //best practice to avoid instantiation
    }

    public static void main(String[] args) {
        CallPrivateConstructor instance = new CallPrivateConstructor();
        System.out.println("created instance: " + instance.toString());
    }
}
