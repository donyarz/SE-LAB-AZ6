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
        if (this.currentState == RouteState.ONE_WAY) {
            System.out.println("Routes are already one way!");
            return;
        }
        this.currentState = RouteState.ONE_WAY;
        updateRoutes();
    }

    public void setTwoWay() {
        if (this.currentState == RouteState.TWO_WAY) {
            System.out.println("Routes are already two way!");
            return;
        }
        this.currentState = RouteState.TWO_WAY;
        updateRoutes();
    }

    private void updateRoutes() {
        boolean isOneWay = (currentState == RouteState.ONE_WAY);

        for (Node node : graph.getGraph()) {
            for (Edge edge : node.getEdges()) {
                Node fromNode = edge.getNodes().getValue0();
                Node toNode = edge.getNodes().getValue1();
                if (isOneWay) {
                    if (node.equals(fromNode)) {
                        edge.setDirected(false);
                    } else if (node.equals(toNode)) {
                        edge.setDirected(true);
                    }
                } else {
                    edge.setDirected(false);
                }
            }
        }
        System.out.println("Routes updated: " + (isOneWay ? "One-way" : "Two-way"));
    }

    public RouteState getCurrentState() {
        return currentState;
    }

    public Graph getGraph() {
        return this.graph;
    }

}
