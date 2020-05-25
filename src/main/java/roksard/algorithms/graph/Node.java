package roksard.algorithms.graph;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private static int count = 0;
    private int id = count++;
    private Map<Node, Integer> edges = new HashMap<>();

    public Map<Node, Integer> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        StringBuilder nodes = new StringBuilder();
        edges.forEach((node, weight) -> {
            if (nodes.length() == 0) {
                nodes.append(" (");
            } else {
                nodes.append(" | ");
            }
            nodes.append(" -" + weight + ">Node." + node.id);
        });
        nodes.append(")");
        return "Node." + id + nodes.toString();
    }
}
