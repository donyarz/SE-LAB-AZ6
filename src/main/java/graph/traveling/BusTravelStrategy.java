package graph.traveling;

import graph.Graph;
import graph.Node;

public class BusTravelStrategy implements TravelStrategy {
    private Graph graph;

    public BusTravelStrategy(Graph graph) {
        this.graph = graph;
    }

    @Override
    public int calculateTravelTime(Node start, Node end) {
        graph.dijkstra(start);
        return end.getDistance();
    }
}
