package roksard.algorithms.graph.breadthFirstSearch;

import roksard.algorithms.graph.Graph;

/*
    Поиск в ширину на графе (поиск пути)
 */

public class BreadthFirstSearchMain {
    public static void main(String[] args) {
        Graph graph = new Graph(10, false, 1, 1);
        System.out.println(graph.toString());
    }
}
