package graph.test;

import graph.Edge;
import graph.Graph;
import graph.Main;
import graph.Node;
import graph.state.RouteManager;
import graph.state.RouteState;
import graph.traveling.BusTravelStrategy;
import graph.traveling.TrainTravelStrategy;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MainTest {

    private Graph graph;
    private RouteManager routeManager;
    private TrainTravelStrategy trainStrategy;
    private BusTravelStrategy busStrategy;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        Node nodeA = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(nodeA);
        nodes.add(nodeB);
        nodes.add(nodeC);

        Edge.createEdge(nodeA, nodeB, false, 5);
        Edge.createEdge(nodeB, nodeC, true, 10);

        graph = new Graph(nodes);
        routeManager = new RouteManager(graph);
        trainStrategy = new TrainTravelStrategy(graph);
        busStrategy = new BusTravelStrategy(graph);

        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testCreateCityMapFromUserInput() {
        String simulatedInput = "3\n2\n0\n1\ntrue\n10\n1\n2\nfalse\n5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Graph generatedGraph = Main.createCityMapFromUserInput();

        assertNotNull(generatedGraph);
        assertEquals(3, generatedGraph.getGraph().size());

        Node nodeA = generatedGraph.getGraph().get(0);
        Node nodeB = generatedGraph.getGraph().get(1);
        Node nodeC = generatedGraph.getGraph().get(2);

        assertEquals(1, nodeA.getEdges().size());
        assertEquals(2, nodeB.getEdges().size());
        assertEquals(1, nodeC.getEdges().size());
    }

    @Test
    public void testHandleMayorActions() {
        String simulatedInput = "1\n4\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        Main.handleMayorActions(scanner, routeManager, trainStrategy);
        assertEquals(RouteState.ONE_WAY, routeManager.getCurrentState());
        String output = outContent.toString();
        assertTrue(output.contains("Routes updated: One-way"));
    }

    @Test
    public void testHandleCitizenActions() {
        String simulatedInput = "1\n0\n2\n5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        Main.handleCitizenActions(scanner, trainStrategy, busStrategy, graph);
        String output = outContent.toString();
        assertTrue(output.contains("Train travel time: "));
        if (output.contains("It is not possible!")) {
            assertFalse(output.contains("Train travel time: 0"));
        } else {
            assertTrue(output.contains("Train travel time:"));
        }
    }

    @Test
    public void testSelectNodeByIndex() {
        String simulatedInput = "1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        Node selectedNode = Main.selectNodeByIndex(scanner, graph);
        assertNotNull(selectedNode);
        assertEquals(graph.getGraph().get(1), selectedNode);
    }

    @Test
    public void testShowCityMap() {
        Main.showCityMap(graph);
        String output = outContent.toString();
        assertTrue(output.contains("Current City Map:"));
        assertTrue(output.contains("City 1 <--> City 2"));
        assertTrue(output.contains("City 2 --> City 3"));
    }

    @Before
    public void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    public void testHandleMayorActions_SetTwoWay() {
        routeManager.setOneWay();
        String simulatedInput = "2\n4\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        Main.handleMayorActions(scanner, routeManager, trainStrategy);
        assertEquals(RouteState.TWO_WAY, routeManager.getCurrentState());
        String output = outContent.toString();
        assertTrue("Expected output to contain 'Routes updated: Two-way'", output.contains("Routes updated: Two-way"));
    }

    @Test
    public void testHandleMayorActions_ChangeTrainSpeed() {
        String simulatedInput = "3\n5\n4\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        Main.handleMayorActions(scanner, routeManager, trainStrategy);
        assertEquals(5, trainStrategy.getTrainUnitTime());
    }

    @Test
    public void testHandleMayorActions_InvalidOption() {
        String simulatedInput = "9\n4\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        Main.handleMayorActions(scanner, routeManager, trainStrategy);
        String output = outContent.toString();
        assertTrue(output.contains("Invalid option, please try again."));
    }

    @Test
    public void testHandleCitizenActions_FindFasterMode() {
        String simulatedInput = "3\n0\n2\n5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        Main.handleCitizenActions(scanner, trainStrategy, busStrategy, graph);
        String output = outContent.toString();
        assertTrue(output.contains("Faster mode: "));
        assertTrue(output.contains("Faster mode time: "));
        if (output.contains("It is not possible!")) {
            assertFalse(output.contains("Train travel time: 0") && output.contains("Bus travel time: 0"));
        }
    }

    @Test
    public void testHandleCitizenActions_InvalidOption() {
        String simulatedInput = "9\n5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        Main.handleCitizenActions(scanner, trainStrategy, busStrategy, graph);
        String output = outContent.toString();
        assertTrue(output.contains("Invalid option, please try again."));
    }

}
