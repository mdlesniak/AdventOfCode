package com.magicalpoet.advent.twentytwo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
public class Day12HillClimb {
    private static final char END_HEIGHT = "E".charAt(0);
    private static final char START_HEIGHT = "S".charAt(0);
    private Node startingNode;
    private Node endingNode;

    public int findBestStartingPoint(String topoMap) {
        int shortestSteps = Integer.MAX_VALUE;
        Graph myGraph = initializeGraph(topoMap);
        addNeighbors(myGraph);
        List<Node> potentialStartingPlaces = myGraph.getNodes().values().stream()
                .filter(node -> node.getHeight() == "a".charAt(0)).toList();
        for (Node node : potentialStartingPlaces) {
            calculateShortestPathFromSource(myGraph, node);
            if (endingNode.getDistance() < shortestSteps) {
                shortestSteps = endingNode.getDistance();
            }
        }

        return shortestSteps;
    }
    public int climbShortest(String topoMap) {
        Graph myGraph = initializeGraph(topoMap);
        addNeighbors(myGraph);
        calculateShortestPathFromSource(myGraph, startingNode);
        return endingNode.getDistance();
    }

    private void addNeighbors(Graph graph) {
        graph.getNodes().forEach((key, node) -> {
            String north = "" + node.getX() + "-" + (node.getY() - 1);
            String west = "" + (node.getX() - 1) + "-" + node.getY();
            String south = "" + node.getX() + "-" + (node.getY() + 1);
            String east = "" + (node.getX() + 1) + "-" + (node.getY());
            for (String potentialDestination : Arrays.asList(north, west, south, east)) {
                graph.getNode(potentialDestination).ifPresent(neighbor -> {
                    if (neighbor.getHeight() - node.getHeight() < 2) {
                        node.addNeighbor(neighbor, 1);
                    }
                });
            }
        });
    }

    private Graph initializeGraph(String topoMap) {
        String[] rows = topoMap.split("\n");
        Graph graph = new Graph();
        for (int y = 0; y < rows.length; y++) {
            char[] heights = rows[y].toCharArray();
            for (int x = 0; x < heights.length; x++) {
                Node node = new Node(x, y, heights[x]);
                if (heights[x] == END_HEIGHT) {
                    node.isEnd = true;
                    node.height = "z".charAt(0);
                    endingNode = node;
                } else if (heights[x] == START_HEIGHT) {
                    node.isStart = true;
                    node.height = "a".charAt(0);
                    startingNode = node;
                }
                graph.addNode(node);
            }
        }
        return graph;
    }

    private void calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>(graph.getNodes().values());

        while (!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair :
                    currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
    }

    private Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance <= lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private void calculateMinimumDistance(Node evaluationNode,
                                                 Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        int possibleNewDistance = sourceDistance + edgeWeigh;
        if (possibleNewDistance < 0) {
            possibleNewDistance = Integer.MAX_VALUE;
        }
        if (possibleNewDistance < evaluationNode.getDistance()) {
            evaluationNode.setDistance(possibleNewDistance);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    @Getter
    @Setter
    private class Graph {
        private Map<String, Node> nodes = new HashMap<>();

        public void addNode(Node node) {
            nodes.put(node.getName(), node);
        }

        public Optional<Node> getNode(String key) {
            return Optional.ofNullable(nodes.get(key));
        }
    }

    @AllArgsConstructor
    @Getter
    @Setter
    private class Node {
        private int x;
        private int y;
        private char height;
        private String name;
        private boolean isStart = false;
        private boolean isEnd = false;
        private List<Node> shortestPath = new LinkedList<>();
        private Integer distance = Integer.MAX_VALUE;
        Map<Node, Integer> adjacentNodes = new HashMap<>();

        private Node(int x, int y, char height) {
            this.x = x;
            this.y = y;
            this.height = height;
            this.name = "" + x + "-" + y;
        }

        public void addNeighbor(Node destination, int distance) {
            adjacentNodes.put(destination, distance);
        }
    }
}
