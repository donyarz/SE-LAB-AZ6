package graph.state;

import graph.Edge;
import graph.Graph;
import graph.Node;

public class RouteManager {
    private RouteState currentState;
    private Graph graph;

    public RouteManager(Graph graph) {
        this.graph = graph;
        this.currentState = RouteState.TWO_WAY;
    }

    public void setOneWay() {
        this.currentState = RouteState.ONE_WAY;
        updateRoutes();
    }

    public void setTwoWay() {
        this.currentState = RouteState.TWO_WAY;
        updateRoutes();
    }

    private void updateRoutes() {
        boolean isOneWay = (currentState == RouteState.ONE_WAY);

        for (Node node : graph.getGraph()) {
            for (Edge edge : node.getEdges()) {
                edge.setDirected(isOneWay);
            }
        }
        System.out.println("Routes updated: " + (isOneWay ? "One-way" : "Two-way"));
    }

    public RouteState getCurrentState() {
        return currentState;
    }
}
