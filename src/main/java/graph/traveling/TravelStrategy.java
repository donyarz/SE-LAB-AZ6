package graph.traveling;

import graph.Node;

public interface TravelStrategy {
    int calculateTravelTime(Node start, Node end);
}
