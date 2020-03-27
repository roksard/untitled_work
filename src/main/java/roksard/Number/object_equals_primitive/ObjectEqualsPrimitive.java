package roksard.Number.object_equals_primitive;

public class ObjectEqualsPrimitive {
    static void equalsTestAutoBoxing(Object a) {
        System.out.println("object: " + a);
        System.out.println("object getClass: " + a.getClass());
        System.out.println("object.getClass().equals(Integer.class): " + a.getClass().equals(Integer.class));
    }
    public static void main(String[] args) {
        System.out.println("Integer.valueOf(5).equals(5)");
        System.out.println(Integer.valueOf(5).equals(5));
        System.out.println("equalsTestAutoBoxing(10)");
        equalsTestAutoBoxing(10);
        System.out.println("equalsTestAutoBoxing(10L)");
        equalsTestAutoBoxing(10L);
    }
}
