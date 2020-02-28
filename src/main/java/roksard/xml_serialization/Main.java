package roksard.xml_serialization;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

class A {
    int x;
    int y;
    A(int x, int y) {
        this.x = x;
        this.y = y;
    }
    A() {}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "A{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class B {
    int n;
    B(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        XmlMapper mapper = new XmlMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        A obja = new A(2,3);
        String storage;
        storage = mapper.writeValueAsString(obja);
        System.out.println("obj A: "+ obja);
        System.out.println("saved as: "+storage);
        A objb = mapper.readValue(storage, A.class);
        System.out.println("read from storage obj B: " + objb);
    }
}
