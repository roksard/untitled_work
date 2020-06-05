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
        while ((node = toCheck.pollFirst()) != null) {
            System.out.println("checking node: " + node);
            if (node == nodeB) {
                System.out.println("found!");
                System.exit(0);
                return Collections.EMPTY_LIST;
            } else {
                toCheck.addAll(node.getEdges().keySet());
            }
        }
        return Collections.EMPTY_LIST;
    }
    public static void main(String[] args) throws InterruptedException {
        Graph graph = new Graph(20, true, 1, 2);
        Random rand = new Random();
        List<Node> nodeList = graph.getNodeList();
        new Thread() {
            @Override
            public void run() {
                breadthFirstSearch(nodeList.get(rand.nextInt(nodeList.size())), nodeList.get(rand.nextInt(nodeList.size())));
            }
        }.start();
        System.out.println(graph.toString());
        Thread.sleep(3000);
        System.exit(1);
    }
}
