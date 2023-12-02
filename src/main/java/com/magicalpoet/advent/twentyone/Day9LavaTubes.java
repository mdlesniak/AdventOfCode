package com.magicalpoet.advent.twentyone;

import java.util.*;
import java.util.stream.Collectors;

public class Day9LavaTubes {
    public int reportRiskLevel(String caveMap) {
        String[] lines = caveMap.split("\n");
        Map<Integer, List<Integer>> cave = getCaveFromStringMap(lines);
        List<Integer> lowPoints = findLowPoints(cave);
        return calculateRiskLevel(lowPoints);
    }

    public int findBasinsScore(String caveMap) {
        String[] lines = caveMap.split("\n");
        Map<Integer, List<Integer>> cave = getCaveFromStringMap(lines);
        // findBasins(cave);
        // get three largest
        // return product of the size of largest
        return 0;
    }

    public int calculateRiskLevel(List<Integer> lowPoints) {
        return lowPoints.stream().mapToInt(Integer::intValue).sum() + lowPoints.size();
    }

    public List<Integer> findLowPoints(Map<Integer, List<Integer>> cave) {
        List<Integer> lowPoints = new ArrayList<>();
        // TODO: map and stream/collect?
        cave.forEach((yPosition, value) -> {
            ListIterator<Integer> iterator = value.listIterator();
            while (iterator.hasNext()) {
                final int xPosition = iterator.nextIndex();
                final Integer point = iterator.next();
                final List<Integer> neighbors = getNeighbors(xPosition, yPosition, cave);
                if (isLowPoint(neighbors, point)) {
                    lowPoints.add(point);
                }
            }
        });
        return lowPoints;
    }

    public boolean isLowPoint(List<Integer> neighbors, Integer point) {
        for (Integer neighbor : neighbors) {
            if (point >= neighbor) {
                return false;
            }
        }
        return true;
    }

    private static List<Integer> getNeighbors(Integer xValue, Integer yValue, Map<Integer, List<Integer>> map) {
        List<Integer> points = new ArrayList<>();
        getNorthernNeighbor(xValue, yValue, map).ifPresent(points::add);
        getWesternNeighbor(xValue, yValue, map).ifPresent(points::add);
        getEasternNeighbor(xValue, yValue, map).ifPresent(points::add);
        getSouthernNeighbor(xValue, yValue, map).ifPresent(points::add);
        return points;
    }

    private static Optional<Integer> getNorthernNeighbor(Integer xValue, Integer yValue, Map<Integer, List<Integer>> map) {
        if (map.containsKey(yValue - 1)) {
            final List<Integer> integers = map.get(yValue - 1);
            return Optional.of(integers.get(xValue));
        }
        return Optional.empty();
    }

    private static Optional<Integer> getEasternNeighbor(Integer xValue, Integer yValue, Map<Integer, List<Integer>> map) {
        final List<Integer> integers = map.get(yValue);
        try {
            return Optional.of(integers.get(xValue + 1));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    private static Optional<Integer> getWesternNeighbor(Integer xValue, Integer yValue, Map<Integer, List<Integer>> map) {
        final List<Integer> integers = map.get(yValue);
        try {
            return Optional.of(integers.get(xValue - 1));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    private static Optional<Integer> getSouthernNeighbor(Integer xValue, Integer yValue, Map<Integer, List<Integer>> map) {
        if (map.containsKey(yValue + 1)) {
            final List<Integer> integers = map.get(yValue + 1);
            return Optional.of(integers.get(xValue));
        }
        return Optional.empty();
    }

    private static Map<Integer, List<Integer>> getCaveFromStringMap(String[] lines) {
        Map<Integer, List<Integer>> cave = new HashMap<>();
        int index = 0;
        for (String line : lines) {
            cave.put(index, splitMapLine(line));
            index++;
        }
        return cave;
    }

    private static List<Integer> splitMapLine(String line) {
        String[] digits = line.split("");
        return Arrays.stream(digits).map(Integer::parseInt).collect(Collectors.toList());
    }
}
