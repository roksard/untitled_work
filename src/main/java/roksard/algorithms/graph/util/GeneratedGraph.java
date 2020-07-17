package roksard.algorithms.graph.util;

import roksard.algorithms.graph.Graph;
import roksard.algorithms.graph.Node;

import java.util.ArrayList;
import java.util.List;

public class GeneratedGraph {

    public static Graph directedWeightedNoNegativeNoCycle_A(List<Node> searchNodes) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(true));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false)); //id 5

        nodes.get(0).getEdges().put(nodes.get(5), 3);
        nodes.get(0).getEdges().put(nodes.get(3), 10);
        nodes.get(1).getEdges().put(nodes.get(4), 4);
        nodes.get(2).getEdges().put(nodes.get(4), 3);
        nodes.get(3).getEdges().put(nodes.get(4), 1);
        nodes.get(5).getEdges().put(nodes.get(2), 3);
        nodes.get(5).getEdges().put(nodes.get(1), 1);
        searchNodes.add(nodes.get(0));
        searchNodes.add(nodes.get(4));

        Graph graph = new Graph();
        graph.setNodeList(nodes);
        return graph;
    }

    public static Graph directedWeightedNoNegativeNoCycle_B(List<Node> searchNodes) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(true));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false)); //id 5

        nodes.get(0).getEdges().put(nodes.get(5), 3);
        nodes.get(0).getEdges().put(nodes.get(3), 10);
        nodes.get(1).getEdges().put(nodes.get(4), 10);
        nodes.get(2).getEdges().put(nodes.get(4), 3);
        nodes.get(3).getEdges().put(nodes.get(4), 1);
        nodes.get(5).getEdges().put(nodes.get(2), 3);
        nodes.get(5).getEdges().put(nodes.get(1), 1);
        searchNodes.add(nodes.get(0));
        searchNodes.add(nodes.get(4));

        Graph graph = new Graph();
        graph.setNodeList(nodes);
        return graph;
    }

    public static Graph directedWeightedNoNegativeNoCycle_C(List<Node> searchNodes) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(true));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false)); //id 5

        nodes.get(0).getEdges().put(nodes.get(5), 3);
        nodes.get(0).getEdges().put(nodes.get(3), 10);
        nodes.get(1).getEdges().put(nodes.get(4), 10);
        nodes.get(2).getEdges().put(nodes.get(4), 3);
        nodes.get(3).getEdges().put(nodes.get(4), 1);
        nodes.get(5).getEdges().put(nodes.get(2), 6);
        nodes.get(5).getEdges().put(nodes.get(1), 1);
        searchNodes.add(nodes.get(0));
        searchNodes.add(nodes.get(4));

        Graph graph = new Graph();
        graph.setNodeList(nodes);
        return graph;
    }

    public static Graph directedWeightedNegativeNoCycle_A(List<Node> searchNodes) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(true));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false)); //id 5

        nodes.get(0).getEdges().put(nodes.get(5), 3);
        nodes.get(0).getEdges().put(nodes.get(3), 10);
        nodes.get(1).getEdges().put(nodes.get(4), 10);
        nodes.get(2).getEdges().put(nodes.get(4), -10);
        nodes.get(3).getEdges().put(nodes.get(4), 1);
        nodes.get(5).getEdges().put(nodes.get(2), 6);
        nodes.get(5).getEdges().put(nodes.get(1), 1);
        searchNodes.add(nodes.get(0));
        searchNodes.add(nodes.get(4));

        Graph graph = new Graph();
        graph.setNodeList(nodes);
        return graph;
    }

    public static Graph directedWeightedNegativeNoCycle_B(List<Node> searchNodes) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node(true));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false));
        nodes.add(new Node(false)); //id 5

        nodes.get(0).getEdges().put(nodes.get(5), -3);
        nodes.get(0).getEdges().put(nodes.get(3), -10);
        nodes.get(1).getEdges().put(nodes.get(4), -10);
        nodes.get(2).getEdges().put(nodes.get(4), -10);
        nodes.get(3).getEdges().put(nodes.get(4), -1);
        nodes.get(5).getEdges().put(nodes.get(2), -6);
        nodes.get(5).getEdges().put(nodes.get(1), -1);
        searchNodes.add(nodes.get(0));
        searchNodes.add(nodes.get(4));

        Graph graph = new Graph();
        graph.setNodeList(nodes);
        return graph;
    }
}
