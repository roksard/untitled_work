package roksard.algorithms.knNeighbours;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class User {
    private static int idc = 0;
    private final int id = idc++;
    private int horror;
    private int comedy;
    private int drama;
    private int documental;
    List<Neighbour> neighbours = new ArrayList<>();


    @Override
    public String toString() {
        return "usr" + id + "{" +
                "horror=" + horror +
                ", comedy=" + comedy +
                ", drama=" + drama +
                ", documental=" + documental +
                ", neighbours=" + neighbours +
                '}';
    }
}
