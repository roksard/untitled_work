package roksard.math.float_division;

public class DoubleAsLong {
    final long value;
    final int precision;
    public DoubleAsLong(double d, int precision) {
        value = Math.round(d * Math.pow(10, precision));
        this.precision = precision;
    }

    public DoubleAsLong(long value, int precision) {
        this.value = value;
        this.precision = precision;
    }

    public DoubleAsLong(long value) {
        this.value = value;
        this.precision = 0;
    }

    public DoubleAsLong add(DoubleAsLong other) {
        return new DoubleAsLong(this.value + other.value, Math.max(this.precision, other.precision));
    }

    public DoubleAsLong sub(DoubleAsLong other) {
        return new DoubleAsLong(this.value - other.value, Math.max(this.precision, other.precision));
    }

    public DoubleAsLong mult(DoubleAsLong other) {
        return new DoubleAsLong(this.value * other.value, Math.max(this.precision, other.precision));
    }

    public DoubleAsLong div(DoubleAsLong other, int precision) {
        return new DoubleAsLong(this.value / other.value, precision);
    }

    public double get() {
        return value / Math.pow(10, precision);
    }

    public static void main(String[] args) {
        DoubleAsLong d = new DoubleAsLong(0.5, 1);
        System.out.println(d.value);
        System.out.println(d.precision);
        System.out.println(d.get());
        System.out.println(new DoubleAsLong(100, 2).mult(new DoubleAsLong(5, 2)).div(new DoubleAsLong(100, 2), 2));
    }

    @Override
    public String toString() {
        return String.valueOf(this.get());
    }
}
