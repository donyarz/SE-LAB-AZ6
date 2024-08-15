package graph;

import graph.state.RouteManager;
import graph.traveling.BusTravelStrategy;
import graph.traveling.TrainTravelStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graph graph = createCityMapFromUserInput();

        RouteManager routeManager = new RouteManager(graph);
        TrainTravelStrategy trainStrategy = new TrainTravelStrategy(graph);
        BusTravelStrategy busStrategy = new BusTravelStrategy(graph);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Are you a mayor or a citizen?");
            System.out.println("1. Mayor");
            System.out.println("2. Citizen");
            System.out.println("3. Exit");
            int roleChoice = scanner.nextInt();

            if (roleChoice == 1) {
                handleMayorActions(scanner, routeManager, trainStrategy);
            } else if (roleChoice == 2) {
                handleCitizenActions(scanner, trainStrategy, busStrategy, graph);
            } else if (roleChoice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static Graph createCityMapFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of cities:");
        int numberOfCities = scanner.nextInt();

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < numberOfCities; i++) {
            nodes.add(new Node());
            System.out.println("Added city " + (i + 1));
        }

        System.out.println("Enter the number of routes:");
        int numberOfEdges = scanner.nextInt();

        for (int i = 0; i < numberOfEdges; i++) {
            System.out.println("Enter the starting city index (0-" + (numberOfCities - 1) + "):");
            int fromIndex = scanner.nextInt();
            Node fromNode = nodes.get(fromIndex);

            System.out.println("Enter the ending city index (0-" + (numberOfCities - 1) + "):");
            int toIndex = scanner.nextInt();
            Node toNode = nodes.get(toIndex);

            System.out.println("Is the route directed? (true/false):");
            boolean directed = scanner.nextBoolean();

            System.out.println("Enter the weight of the route:");
            int weight = scanner.nextInt();

            Edge.createEdge(fromNode, toNode, directed, weight);
            System.out.println("Added route from city " + fromIndex + " to city " + toIndex);
        }

        return new Graph(new ArrayList<>(nodes));
    }

    public static void handleMayorActions(Scanner scanner, RouteManager routeManager, TrainTravelStrategy trainStrategy) {
        while (true) {
            System.out.println("Mayor Actions:");
            System.out.println("1. Make all routes one-way");
            System.out.println("2. Make all routes two-way");
            System.out.println("3. Change train speed");
            System.out.println("4. Back to main menu");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    routeManager.setOneWay();
                    showCityMap(routeManager.getGraph());
                    break;
                case 2:
                    routeManager.setTwoWay();
                    showCityMap(routeManager.getGraph());
                    break;
                case 3:
                    System.out.println("Enter new train speed:");
                    int newSpeed = scanner.nextInt();
                    trainStrategy.setTrainUnitTime(newSpeed);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    public static void handleCitizenActions(Scanner scanner, TrainTravelStrategy trainStrategy, BusTravelStrategy busStrategy, Graph graph) {
        while (true) {
            System.out.println("Citizen Actions:");
            System.out.println("1. Query travel time by train");
            System.out.println("2. Query travel time by bus");
            System.out.println("3. Find faster travel mode");
            System.out.println("4. Check route avoiding specific city");
            System.out.println("5. Back to main menu");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Select start city and end city by index:");
                    Node start = selectNodeByIndex(scanner, graph);
                    Node end = selectNodeByIndex(scanner, graph);
                    int trainTime = trainStrategy.calculateTravelTime(start, end);
                    System.out.println("Train travel time: " + trainTime);
                    if (trainTime == 0) {
                        System.out.println("It is not possible!");
                    }
                    break;
                case 2:
                    System.out.println("Select start city and end city by index:");
                    start = selectNodeByIndex(scanner, graph);
                    end = selectNodeByIndex(scanner, graph);
                    int busTime = busStrategy.calculateTravelTime(start, end);
                    if (busTime == 0) {
                        System.out.println("It is not possible!");
                    }
                    System.out.println("Bus travel time: " + busTime);
                    break;
                case 3:
                    System.out.println("Select start city and end city by index:");
                    start = selectNodeByIndex(scanner, graph);
                    end = selectNodeByIndex(scanner, graph);
                    trainTime = trainStrategy.calculateTravelTime(start, end);
                    busTime = busStrategy.calculateTravelTime(start, end);
                    System.out.println("Faster mode: " + (trainTime < busTime ? "Train" : "Bus"));
                    System.out.println("Faster mode time: " + (Math.min(trainTime, busTime)));
                    if (trainTime == 0 && busTime == 0) {
                        System.out.println("It is not possible!");
                    }
                    break;
                case 4:
                    System.out.println("Select start city, end city, and city to avoid by index:");
                    start = selectNodeByIndex(scanner, graph);
                    end = selectNodeByIndex(scanner, graph);
                    Node avoidCity = selectNodeByIndex(scanner, graph);
                    avoidCity.setVisited(true);
                    trainTime = trainStrategy.calculateTravelTime(start, end);
                    busTime = busStrategy.calculateTravelTime(start, end);
                    if (trainTime == 0 && busTime == 0) {
                        System.out.println("It is not possible!");
                    } else {
                        System.out.println("Faster mode: " + (trainTime < busTime ? "Go with Train" : "Go with bus Bus"));
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    public static Node selectNodeByIndex(Scanner scanner, Graph graph) {
        List<Node> nodes = graph.getGraph();
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println(i + ": City " + (i + 1));
        }

        int nodeIndex;
        Node node;
        do {
            System.out.print("Enter city index: ");
            nodeIndex = scanner.nextInt();
            if (nodeIndex < 0 || nodeIndex >= nodes.size()) {
                System.out.println("Invalid index. Please try again.");
                node = null;
            } else {
                node = nodes.get(nodeIndex);
            }
        } while (node == null);

        return node;
    }

    public static void showCityMap(Graph graph) {
        List<Node> nodes = graph.getGraph();
        System.out.println("Current City Map:");
        for (Node node : nodes) {
            for (Edge edge : node.getEdges()) {
                Node fromNode = edge.getNodes().getValue0();
                Node toNode = edge.getNodes().getValue1();
                if (edge.isDirected()) {
                    System.out.println("City " + (nodes.indexOf(fromNode) + 1) + " --> City " + (nodes.indexOf(toNode) + 1));
                } else {
                    System.out.println("City " + (nodes.indexOf(fromNode) + 1) + " <--> City " + (nodes.indexOf(toNode) + 1));
                }
            }
        }
        System.out.println();
    }

}
