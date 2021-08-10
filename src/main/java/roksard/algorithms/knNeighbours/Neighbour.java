package roksard.algorithms.knNeighbours;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Neighbour {
    User user;
    double similarity;

    @Override
    public String toString() {
        return "Neighbour{" +
                "usr" + user.getId() +
                ", similarity=" + String.format("%.2f", similarity) +
                '}';
    }
}
