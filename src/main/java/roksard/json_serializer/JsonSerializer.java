package roksard.json_serializer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class JsonSerializer<T> {
    Class<T> type;

    public JsonSerializer(Class<T> type) {
        this.type = type;
    }

    public T load(String fileName, T defaultValue) {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        try (Reader reader = new FileReader(fileName)) {
            return objectMapper.readValue(reader, type);
        } catch (FileNotFoundException e) {
            return defaultValue;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void save(String fileName, T object) {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        try (Writer writer = new FileWriter(fileName)) {
            objectMapper.writeValue(writer, object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
