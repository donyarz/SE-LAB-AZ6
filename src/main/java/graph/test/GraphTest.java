package graph.test;

import graph.Edge;
import graph.Graph;
import graph.Node;
import graph.traveling.BusTravelStrategy;
import graph.traveling.TrainTravelStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GraphTest {
    private Graph graph;
    private Node nodeA;
    private Node nodeB;
    private Node nodeC;
    private TrainTravelStrategy trainStrategy;
    private BusTravelStrategy busStrategy;

    @Before
    public void setUp() {
        nodeA = new Node();
        nodeB = new Node();
        nodeC = new Node();

        Edge.createEdge(nodeA, nodeB, false, 5);
        Edge.createEdge(nodeB, nodeC, false, 6);

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(nodeA);
        nodes.add(nodeB);
        nodes.add(nodeC);

        nodeC.setVisited(true);

        graph = new Graph(nodes);
        trainStrategy = new TrainTravelStrategy(graph);
    }

    @Test
    public void testBFS() {
        graph.bfs(nodeA);
        int numberOfEdges = nodeC.getDistance();
        assertEquals(0, numberOfEdges * trainStrategy.getTrainUnitTime());
    }

    @Test
    public void testDijkstra() {
        graph.dijkstra(nodeA);
        assertEquals(0, nodeC.getDistance());
    }

}
