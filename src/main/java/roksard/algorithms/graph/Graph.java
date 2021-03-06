package roksard.algorithms.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Graph {
    List<Node> nodeList = new ArrayList<>();

    /**
     * creates empty graph
     */
    public Graph() {
    }

    public Graph(int nodesCount, boolean directional, int edgeWeightFrom, int edgeWeightTo, int minEdgesPerNode, int maxEdgesPerNode, Random rand) {
        if (nodesCount < 2) {
            throw new RuntimeException("nodesCount is less than 2");
        }
        if (minEdgesPerNode > nodesCount -1 || minEdgesPerNode < 0) {
            throw new RuntimeException("minEdgesPerNode invalid: " + minEdgesPerNode);
        }
        if (maxEdgesPerNode < minEdgesPerNode || maxEdgesPerNode > nodesCount - 1) {
            throw new RuntimeException("maxEdgesPerNode invalid: " + maxEdgesPerNode);
        }
        for (int i = 0; i < nodesCount; i++) {
            Node node = new Node(nodeList.isEmpty());
            nodeList.add(node);
        }
        for (int i = 0; i < nodeList.size(); i++) {
            int edgeWeight = (edgeWeightTo-edgeWeightFrom+1) > 0
                    ? rand.nextInt(edgeWeightTo-edgeWeightFrom+1) + edgeWeightFrom
                    : edgeWeightFrom;
            Node node = nodeList.get(i);
            Map<Node, Integer> edges = node.getEdges();
            int edgesToAdd = minEdgesPerNode + rand.nextInt(maxEdgesPerNode - minEdgesPerNode + 1);
            while (edges.size() < edgesToAdd) {
                int otherI = rand.nextInt(nodeList.size());
                if (i == otherI) {
                    otherI = (otherI + 1) % nodeList.size();
                }
                Node other = nodeList.get(otherI);
                edges.put(other, edgeWeight);
                if (!directional) {
                    other.getEdges().put(node, edgeWeight);
                }
            }
        }
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    @Override
    public String toString() {
        String tab = "  ";
        StringBuilder nodes = new StringBuilder("[");
        for (Node node : nodeList) {
            nodes
                    .append("\n")
                    .append(tab)
                    .append(node.toString())
                    .append(node.edges());
        }
        if (nodeList.size() > 0) {
            nodes.append("\n");
        }
        nodes.append("]\n");
        return nodes.toString();
    }
}
