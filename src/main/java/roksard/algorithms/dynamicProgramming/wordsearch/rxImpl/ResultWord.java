package roksard.algorithms.dynamicProgramming.wordsearch.rxImpl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ResultWord {
    private int commonLength;
    private String word;
}
