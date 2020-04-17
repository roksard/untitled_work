package roksard.optional;

import java.util.Optional;

class ObjectX {
    String name;
    public ObjectX(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ObjectX{" +
                "name='" + name + '\'' +
                '}';
    }
}

public class OptionalMap {
    static void optionalOfOject(ObjectX obj) {
        System.out.println("optionalOfOject: " + obj);

        String nameObtained = Optional.ofNullable(obj)
                .map(objLocal -> objLocal.getName())
                .orElse("-");
        System.out.println("nameObtained: " + nameObtained);
    }

    public static void main(String[] args) {
        optionalOfOject(null);
        optionalOfOject(new ObjectX("Johan"));

    }
}
