package roksard.algorithms.graph.breadthFirstSearch;

import roksard.algorithms.graph.Graph;
import roksard.algorithms.graph.Node;

import java.util.*;

/*
    Поиск в ширину на графе (поиск пути)
 */

public class BreadthFirstSearchMain {
    static List<Node> breadthFirstSearch(Node nodeA, Node nodeB) {
        System.out.println("looking path from " + nodeA + " to " + nodeB);
        Deque<Node> toCheck = new ArrayDeque<>();
        toCheck.add(nodeA);
        Node node;
        Set<Node> checked = new HashSet<>();
        while ((node = toCheck.pollFirst()) != null) {
            if (!checked.contains(node)) {
                checked.add(node);
                System.out.println("checking node: " + node.toString() + node.edges());
                if (node == nodeB) {
                    System.out.println("found!");
                    return Collections.EMPTY_LIST;
                } else {
                    toCheck.addAll(node.getEdges().keySet());
                }
            }
        }
        return Collections.EMPTY_LIST;
    }
    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random(1);
        Graph graph = new Graph(20, false, 1, 1, 1, 1, rand);
        List<Node> nodeList = graph.getNodeList();
        System.out.println(graph.toString());
        breadthFirstSearch(nodeList.get(rand.nextInt(nodeList.size())), nodeList.get(rand.nextInt(nodeList.size())));
    }
}
