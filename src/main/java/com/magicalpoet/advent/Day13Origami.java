package com.magicalpoet.advent;

import java.util.HashMap;
import java.util.Map;

public class Day13Origami {
    public int countAfterFolding(String dots, String foldInstructions) {
        final Map<String, Coordinate> foldedGrid = createPaperAndFold(dots, foldInstructions);
        return foldedGrid.size();
    }

    public String visualizeAfterFolding(String dots, String foldInstructions) {
        final Map<String, Coordinate> foldedGrid = createPaperAndFold(dots, foldInstructions);
        return prettyPrint(foldedGrid);
    }

    public Map<String, Coordinate> createPaperAndFold(String dots, String foldInstructions) {
        final String[] points = dots.split("\n");
        Map<String, Coordinate> grid = plotPoints(points);
        return foldPaper(grid, foldInstructions);
    }

    private String prettyPrint(Map<String, Coordinate> grid) {
        StringBuilder picture = new StringBuilder();
        for (int y = 0; y < 40; y++) {
            for (int x = 0; x < 40; x++) {
                String key = x + "," + y;
                if (grid.containsKey(key)) {
                    picture.append("#");
                } else {
                    picture.append("-");
                }
            }
            picture.append("\n");
        }
        return picture.toString();
    }

    private Map<String, Coordinate> plotPoints(String[] points) {
        Map<String, Coordinate> grid = new HashMap<>();
        for (String point : points) {
            final String[] numbers = point.split(",");
            final Coordinate coordinate = new Coordinate(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
            grid.put(point, coordinate);
        }
        return grid;
    }

    private Map<String, Coordinate> foldPaper(Map<String, Coordinate> grid, String foldInstructions) {
        final String[] instructions = foldInstructions.split("\n");
        Map<String, Coordinate> result = grid;
        for (String instruction : instructions) {
            result = executeFold(result, instruction);
        }
        return result;
    }

    private Map<String, Coordinate> executeFold(Map<String, Coordinate> grid, String foldInstruction) {
        final String[] instruction = foldInstruction.split("=");
        Map<String, Coordinate> result;
        if (instruction[0].endsWith("x")) {
            result = foldAlongX(Integer.parseInt(instruction[1]), grid);
        } else {
            result = foldAlongY(Integer.parseInt(instruction[1]), grid);
        }
        return result;
    }

    private Map<String, Coordinate> foldAlongX(int value, Map<String, Coordinate> grid) {
        Map<String, Coordinate> newGrid = new HashMap<>();
        for (Coordinate coordinate : grid.values()) {
            if (coordinate.x < value) {
                newGrid.put(coordinate.x + "," + coordinate.y, coordinate);
            } else {
                Coordinate newCoordinate = new Coordinate(value - (coordinate.x - value), coordinate.y);
                newGrid.put(newCoordinate.x + "," + newCoordinate.y, newCoordinate);
            }
        }
        return newGrid;
    }

    private Map<String, Coordinate> foldAlongY(int value, Map<String, Coordinate> grid) {
        Map<String, Coordinate> newGrid = new HashMap<>();
        for (Coordinate coordinate : grid.values()) {
            if (coordinate.y < value) {
                newGrid.put(coordinate.x + "," + coordinate.y, coordinate);
            } else {
                Coordinate newCoordinate = new Coordinate(coordinate.x, value - (coordinate.y - value));
                newGrid.put(newCoordinate.x + "," + newCoordinate.y, newCoordinate);
            }
        }
        return newGrid;
    }

}
