package roksard.json_serialization;

public class A {
    int b = 1;
    int c = 2;

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "A{" +
                "b=" + b +
                ", c=" + c +
                '}';
    }
}
