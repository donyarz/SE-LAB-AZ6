package graph.traveling;

import graph.Graph;
import graph.Node;

public class TrainTravelStrategy implements TravelStrategy {
    private Graph graph;
    private int trainUnitTime;

    public TrainTravelStrategy(Graph graph) {
        this.graph = graph;
        this.trainUnitTime = 1;
    }

    @Override
    public int calculateTravelTime(Node start, Node end) {
        graph.bfs(start);
        int numberOfEdges = end.getDistance();
        return numberOfEdges * trainUnitTime;
    }

    public void setTrainUnitTime(int newTrainUnitTime) {
        this.trainUnitTime = newTrainUnitTime;
    }
}
