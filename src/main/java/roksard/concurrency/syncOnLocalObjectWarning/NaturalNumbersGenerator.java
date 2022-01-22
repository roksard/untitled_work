package roksard.concurrency.syncOnLocalObjectWarning;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class NaturalNumbersGenerator {
    private final List<String> listNaturalNumbers = new ArrayList<>();
    private int current = 1;

    public void generateNew(String postString) {
        listNaturalNumbers.add(String.valueOf(current) + postString);
        current++;
    }

}
