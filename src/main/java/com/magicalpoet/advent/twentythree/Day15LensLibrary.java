package com.magicalpoet.advent.twentythree;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Day15LensLibrary {
    private final Map<Integer, List<Map<String, Integer>>> boxes = new HashMap<>();

    public int getHashScore(String input) {
        int score = 0;
        for (int i = 0; i < input.length(); i++) {
            score += input.charAt(i);
            score = (score * 17) % 256;
        }
        return score;
    }

    public int totalHashScores(String input) {
        final String[] steps = input.split(",");
        return Arrays.stream(steps).mapToInt(this::getHashScore).sum();
    }

    public void processStepEquals(String step) {
        final String[] split = step.split("=");
        String label = split[0];
        int focalLength = Integer.parseInt(split[1]);
        boxes.put(getHashScore(label), List.of(Map.of(label, focalLength)));
    }
}
