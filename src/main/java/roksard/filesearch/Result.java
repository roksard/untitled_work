package roksard.filesearch;

import java.util.ArrayList;
import java.util.List;

public class Result {
    List<String> result = new ArrayList<>();

    public List<String> getResult() {
        return result;
    }

    public List<String> add(String subResult) {
        result.add(subResult);
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                '}';
    }
}
