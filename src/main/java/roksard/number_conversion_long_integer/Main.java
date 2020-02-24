package roksard.number_conversion_long_integer;

public class Main {
    public static Long getLong(Object o) {
        if (o != null) {
            System.out.println("input param: " + o.getClass());
        }
        return (o instanceof Number) ? ((Number)o).longValue() : 0;
    }

    public static Integer getInteger(Object o) {
        if (o != null) {
            System.out.println("input param: " + o.getClass());
        }
        return (o instanceof Number) ? ((Number)o).intValue() : 0;
    }

    public static Integer getIntegerByCast(Object o) {
        if (o != null) {
            System.out.println("input param: " + o.getClass());
        }
        return (Integer)o;
    }

    public static void main(String[] args) {
        Object result;
        result = getLong(null);
        System.out.println("getLong(null): " + result + " (" + result.getClass() + ")\n");
        result = getInteger(null);
        System.out.println("getInteger(null): " + result + " (" + result.getClass() + ")\n");

        Long long1 = 35L;
        Integer integer1 = 12;
        System.out.println("Long long1 = " + long1);
        System.out.println("Integer integer1 = " + integer1 + "\n");

        result = getLong(long1);
        System.out.println("getLong(long1): " + result
                + " (" + result.getClass() + ")\n");

        result = getInteger(integer1);
        System.out.println("getInteger(integer1): " + result
                + " (" + result.getClass() + ")\n");

        result = getLong(integer1);
        System.out.println("getLong(integer1): " + result
                + " (" + result.getClass() + ")\n");

        result = getInteger(long1);
        System.out.println("getInteger(long1): " + result
                + " (" + result.getClass() + ")\n");

        //error
        result = getIntegerByCast(long1);
        System.out.println("getIntegerByCast(long1): " + result
                + " (" + result.getClass() + ")\n");
    }
}

/* Output:
getLong(null): 0 (class java.lang.Long)

getInteger(null): 0 (class java.lang.Integer)

Long long1 = 35
Integer integer1 = 12

input param: class java.lang.Long
getLong(long1): 35 (class java.lang.Long)

input param: class java.lang.Integer
getInteger(integer1): 12 (class java.lang.Integer)

input param: class java.lang.Integer
getLong(integer1): 12 (class java.lang.Long)

input param: class java.lang.Long
getInteger(long1): 35 (class java.lang.Integer)

input param: class java.lang.Long
Exception in thread "main" java.lang.ClassCastException: java.lang.Long cannot be cast to java.lang.Integer
	at roksard.number_conversion_long_integer.Main.getIntegerByCast(Main.java:22)
	at roksard.number_conversion_long_integer.Main.main(Main.java:54)

Process finished with exit code 1


 */
