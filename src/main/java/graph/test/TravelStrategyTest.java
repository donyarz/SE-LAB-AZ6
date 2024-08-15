package graph.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import graph.Graph;
import graph.Node;
import graph.Edge;
import graph.traveling.TrainTravelStrategy;
import graph.traveling.BusTravelStrategy;

public class TravelStrategyTest {
    private Node start;
    private Node middle11;
    private Node middle12;
    private Node middle13;
    private Node middle14;
    private Node middle21;
    private Node middle22;
    private Node middle23;
    private Node end;
    private Graph graph;
    private TrainTravelStrategy trainStrategy;
    private BusTravelStrategy busStrategy;

    @Before
    public void setUp() {
        start = new Node();
        middle11 = new Node();
        middle12 = new Node();
        middle13 = new Node();
        middle14 = new Node();
        middle21 = new Node();
        middle22 = new Node();
        middle23 = new Node();
        end = new Node();

        // First path from start to end
        Edge.createEdge(start, middle11, false, 3);
        Edge.createEdge(middle11, middle12, false, 7);
        Edge.createEdge(middle12, middle13, false, 5);
        Edge.createEdge(middle13, middle14, false, 6);
        Edge.createEdge(middle14, end, false, 2);

        // Second Path from start to end
        Edge.createEdge(start, middle21, false, 10);
        Edge.createEdge(middle21, middle22, false, 11);
        Edge.createEdge(middle22, middle23, false, 12);
        Edge.createEdge(middle23, end, false, 11);


        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(start);
        nodes.add(middle11);
        nodes.add(middle12);
        nodes.add(middle13);
        nodes.add(middle14);
        nodes.add(middle21);
        nodes.add(middle22);
        nodes.add(middle23);
        nodes.add(end);
        graph = new Graph(nodes);

        trainStrategy = new TrainTravelStrategy(graph);
        busStrategy = new BusTravelStrategy(graph);
    }

    @Test
    public void testTrainTravelTimeWithBaseUnitTime() {
        int expectedTime = 4;
        int calculatedTime = trainStrategy.calculateTravelTime(start, end);
        assertEquals("Train travel time should be equal to the number of edges in the path", expectedTime, calculatedTime);
    }

    @Test
    public void testTrainTravelTimeWithChangedUnitTime() {
        trainStrategy.setTrainUnitTime(3);
        int expectedTime = 12;
        int calculatedTime = trainStrategy.calculateTravelTime(start, end);
        assertEquals("Train travel time should be equal to the number of edges in the path", expectedTime, calculatedTime);
    }

    @Test
    public void testBusTravelTime() {
        int expectedTime = 23;
        int calculatedTime = busStrategy.calculateTravelTime(start, end);
        assertEquals("Bus travel time should be equal to the edges weight", expectedTime, calculatedTime);
    }
}
