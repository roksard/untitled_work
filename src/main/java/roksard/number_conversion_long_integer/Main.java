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

    public static void main(String[] args) {
        Object result;
        result = getLong(null);
        System.out.println("getLong(null): " + result + " (" + result.getClass() + ")\n");
        result = getInteger(null);
        System.out.println("getInteger(null): " + result + " (" + result.getClass() + ")\n");
        result = getLong(new Long(35));
        System.out.println("getLong(new Long(35)): " + result
                + " (" + result.getClass() + ")\n");
        result = getInteger(new Integer(12));
        System.out.println("getInteger(new Integer(12)): " + result
                + " (" + result.getClass() + ")\n");
        result = getLong(new Integer(12));
        System.out.println("getLong(new Integer(12)): " + result
                + " (" + result.getClass() + ")\n");
        result = getInteger(new Long(35));
        System.out.println("getInteger(new Long(35)): " + result
                + " (" + result.getClass() + ")\n");
    }
}
