package com.magicalpoet.advent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day9LavaTubes {
    public int reportRiskLevel(String caveMap) {
        String[] lines = caveMap.split("\n");
        Map<Integer, List<Integer>> cave = getCaveFromStringMap(lines);
        List<Integer> lowPoints = findLowPoints(cave);
        return calculateRiskLevel(lowPoints);
    }

    public int calculateRiskLevel(List<Integer> lowPoints) {
        return lowPoints.stream().mapToInt(Integer::intValue).sum() + lowPoints.size();
    }

    public List<Integer> findLowPoints(Map<Integer, List<Integer>> cave) {
        return null;
    }

    private Map<Integer, List<Integer>> getCaveFromStringMap(String[] lines) {
        Map<Integer, List<Integer>> cave = new HashMap<>();
        int index = 0;
        for (String line : lines) {
            cave.put(index, splitMapLine(line));
            index++;
        }
        return cave;
    }

    private List<Integer> splitMapLine(String line) {
        String[] digits = line.split("");
        return Arrays.stream(digits).map(Integer::parseInt).collect(Collectors.toList());
    }
}
