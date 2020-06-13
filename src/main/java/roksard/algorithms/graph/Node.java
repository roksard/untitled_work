package roksard.algorithms.graph;

import roksard.util.NameFromInt;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Node {
    private static int count = 0;
    private int id = count++;
    private String name = new NameFromInt(id).getName();
    private Map<Node, Integer> edges = new LinkedHashMap<>();

    public Node() {
    }

    public Node(boolean resetId) {
        if (resetId) {
            count = 0;
            id = count++;
            name = new NameFromInt(id).getName();
        }
    }

    public Map<Node, Integer> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return name;
    }

    public String edges() {
        StringBuilder nodes = new StringBuilder();
        edges.forEach((node, weight) -> {
            if (nodes.length() == 0) {
                nodes.append(" (");
            } else {
                nodes.append(" | ");
            }
            nodes.append(weight).append("->").append(node.toString());
        });
        nodes.append(")");
        return nodes.toString();
    }
}
