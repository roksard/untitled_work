package roksard.json_serialization;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    public static void main(String[] args) {
        loadNSave("");
    }

    public static void loadNSave(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        try {
            System.out.println(objectMapper.writeValueAsString(new A()));
            A obj = objectMapper.readValue("{\"b\":3,\"c\":4}", A.class);
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
