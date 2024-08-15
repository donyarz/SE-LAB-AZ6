package graph.test;

import graph.Edge;
import graph.Graph;
import graph.Node;
import graph.state.RouteManager;
import graph.state.RouteState;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RouteManagerTest {

    private Graph graph;
    private Node nodeA;
    private Node nodeB;
    private RouteManager routeManager;

    @Before
    public void setUp() {
        nodeA = new Node();
        nodeB = new Node();

        Edge.createEdge(nodeA, nodeB, false, 10);
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(nodeA);
        nodes.add(nodeB);
        graph = new Graph(nodes);
        routeManager = new RouteManager(graph);
    }

    @Test
    public void testInitialRouteState() {
        assertEquals(RouteState.TWO_WAY, routeManager.getCurrentState());
        assertFalse(nodeA.getEdges().get(0).isDirected());
    }

    @Test
    public void testSetOneWay() {
        routeManager.setOneWay();
        assertEquals(RouteState.ONE_WAY, routeManager.getCurrentState());
        assertTrue(nodeA.getEdges().get(0).isDirected());
    }

    @Test
    public void testSetTwoWay() {
        routeManager.setOneWay();
        routeManager.setTwoWay();
        assertEquals(RouteState.TWO_WAY, routeManager.getCurrentState());
        assertFalse(nodeA.getEdges().get(0).isDirected());
    }

    @Test
    public void testMultipleEdges() {
        Node nodeC = new Node();
        Edge.createEdge(nodeB, nodeC, false, 5); // Initially two-way
        graph.getGraph().add(nodeC);
        routeManager.setOneWay();
        assertTrue(nodeA.getEdges().get(0).isDirected());
        assertTrue(nodeB.getEdges().get(1).isDirected());
        routeManager.setTwoWay();
        assertFalse(nodeA.getEdges().get(0).isDirected());
        assertFalse(nodeB.getEdges().get(1).isDirected());
    }
}
