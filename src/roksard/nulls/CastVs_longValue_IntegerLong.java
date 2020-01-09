package roksard.nulls;

public class CastVs_longValue_IntegerLong {
    public static void main(String[] args) {
        Long long_;
        Integer int_;

        long_ = 5L;
        int_ = 5;
        System.out.println("1 same: " + long_.equals((long)int_));

        long_ = 5L;
        int_ = 5;
        System.out.println("2 same: " + long_.equals(int_.longValue()));

        long_ = 6L;
        int_ = 5;
        System.out.println("1 different: " + long_.equals((long)int_));

        long_ = 6L;
        int_ = 5;
        System.out.println("2 different: " + long_.equals(int_.longValue()));

    }
}
