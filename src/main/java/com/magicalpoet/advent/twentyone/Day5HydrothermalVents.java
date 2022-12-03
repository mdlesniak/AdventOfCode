package com.magicalpoet.advent.twentyone;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day5HydrothermalVents {
    public int countOverlappingLines(String pointsList) {
        final String[] linesInput = pointsList.split("\n");
        List<Line> lines = Arrays.stream(linesInput).map(this::getLine).collect(Collectors.toList());
        Map<String, Integer> pointsCountMap = getPointsCount(lines);
        List<Integer> overlaps = pointsCountMap.values().stream().filter(value -> value > 1).collect(Collectors.toList());
        return overlaps.size();
    }

    private Map<String, Integer> getPointsCount(List<Line> lines) {
        Map<String, Integer> countMap = new HashMap<>();
        for (Line line : lines) {
            if (line.isHorizontal()) {
                processHorizontalLine(countMap, line);
            } else if (line.isVertical()) {
                processVerticalLine(countMap, line);
            } else {
                processDiagonalLine(countMap, line);
            }
        }
        return countMap;
    }

    private void processDiagonalLine(Map<String, Integer> countMap, Line line) {
        Coordinate start;
        Coordinate end;
        if (line.start.x < line.end.x) {
            start = line.start;
            end = line.end;
        } else {
            start = line.end;
            end = line.start;
        }

        if (start.y > end.y) {
            processNegativeSlope(countMap, start, end);
        } else {
            processPositiveSlope(countMap, start, end);
        }
    }

    private void processPositiveSlope(Map<String, Integer> countMap, Coordinate start, Coordinate end) {
        int currentX = start.x;
        int currentY = start.y;
        while (currentY <= end.y) {
            String key = currentX + "," + currentY;
            countMap.putIfAbsent(key, 0);
            countMap.put(key, countMap.get(key) + 1);
            currentX++;
            currentY++;
        }
    }

    private void processNegativeSlope(Map<String, Integer> countMap, Coordinate start, Coordinate end) {
        int currentX = start.x;
        int currentY = start.y;
        while (currentY >= end.y) {
            String key = currentX + "," + currentY;
            countMap.putIfAbsent(key, 0);
            countMap.put(key, countMap.get(key) + 1);
            currentX++;
            currentY--;
        }
    }

    private void processVerticalLine(Map<String, Integer> countMap, Line line) {
        int startY = Math.min(line.start.y, line.end.y);
        int endY = Math.max(line.start.y, line.end.y);
        int currentY = startY;
        while (currentY <= endY) {
            String key = line.start.x + "," + currentY;
            countMap.putIfAbsent(key, 0);
            countMap.put(key, countMap.get(key) + 1);
            currentY++;
        }
    }

    private void processHorizontalLine(Map<String, Integer> countMap, Line line) {
        int startX = Math.min(line.start.x, line.end.x);
        int endX = Math.max(line.start.x, line.end.x);
        int currentX = startX;
        while (currentX <= endX) {
            String key = currentX + "," + line.start.y;
            countMap.putIfAbsent(key, 0);
            countMap.put(key, countMap.get(key) + 1);
            currentX++;
        }
    }

    private Line getLine(String lineInput) {
        final String[] points = lineInput.split(" -> ");
        final Coordinate lineStart = getCoordinate(points[0]);
        final Coordinate lineEnd = getCoordinate(points[1]);
        return new Line(lineStart, lineEnd);
    }

    private Coordinate getCoordinate(String point) {
        final String[] values = point.split(",");
        return new Coordinate(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
    }

}
