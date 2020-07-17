package roksard.algorithms.graph.dijkstra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import roksard.algorithms.graph.Graph;
import roksard.algorithms.graph.Node;
import roksard.algorithms.graph.util.GeneratedGraph;

import java.util.*;

public class Dijkstra {
    public static Logger logger = LoggerFactory.getLogger(Dijkstra.class);

    static void processNode(Node node, int currentCost, Node searchNode, Map<Node,Integer> costs, Map<Node,Node> parents) {
        logger.info("processingNode {} currentCost {} searchNode {} costs {} parents {}", node, currentCost, searchNode, costs, parents);
        if (node == searchNode) {
            return;
        }
        PriorityQueue<Map.Entry<Node,Integer>> nodesToProcess = new PriorityQueue<>(Map.Entry.comparingByValue());
        for (Map.Entry<Node, Integer> entry : node.getEdges().entrySet()) {
            Integer cost = entry.getValue();
            Node subNode = entry.getKey();
            if (costs.containsKey(subNode)) {
                Integer current = costs.get(subNode);
                if (current > cost + currentCost) {
                    costs.put(subNode, cost + currentCost);
                    parents.put(subNode, node);
                }
            } else {
                costs.put(subNode, cost + currentCost);
                parents.put(subNode, node);
            }
            nodesToProcess.offer(entry);
        }
        nodesToProcess.forEach(entry -> processNode(entry.getKey(), entry.getValue() + currentCost, searchNode, costs, parents));
    }

    static List<Node> dijkstra(Node a, Node b) {
        logger.info("start dijkstra start {} end {}", a, b);
        Map<Node,Integer> costs = new HashMap<>();
        Map<Node,Node> parents = new HashMap<>();
        processNode(a, 0, b, costs, parents);
        logger.info("finished dijkstra");
        logger.info("costs {}", costs);
        logger.info("parents {}", parents);
        List<Node> resultPath = new ArrayList<>();
        Node currentNode = b;
        while (currentNode != null) {
            resultPath.add(currentNode);
            currentNode = parents.get(currentNode);
        }
        Collections.reverse(resultPath);
        return resultPath;
    }

    public static void main(String[] args) {
        Random rand = new Random(8);
        List<Node> searchNodes = new ArrayList<>();
        Graph graph = GeneratedGraph.directedWeightedNegativeNoCycle_B(searchNodes);
        List<Node> nodeList = graph.getNodeList();
        logger.info("graph \n{}", graph.toString());
        List<Node> path = dijkstra(searchNodes.get(0), searchNodes.get(1));
        logger.info("path {}", path);
    }
}
