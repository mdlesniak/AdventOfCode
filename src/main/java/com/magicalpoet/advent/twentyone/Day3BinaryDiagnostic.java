package com.magicalpoet.advent.twentyone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3BinaryDiagnostic {
    public int calculatePowerConsumption(List<String> input) {
        String gammaRate = getGammaRate(input);
        String epsilonRate = getEpsilonRate(input);
        return binaryConverter(gammaRate) * binaryConverter(epsilonRate);
    }

    public int calculateLifeSupportRating(List<String> input) {
        String oxygenRating = getOxygenRating(input);
        String carbonRating = getCarbonRating(input);
        return binaryConverter(oxygenRating) * binaryConverter(carbonRating);
    }

    public String getOxygenRating(List<String> input) {
        List<String> remainingValues = filterForModeAtCharacter(input, 0);
        int index = 1;
        while (remainingValues.size() > 1) {
            remainingValues = filterForModeAtCharacter(remainingValues, index);
            index++;
        }
        return remainingValues.get(0);
    }

    public String getCarbonRating(List<String> input) {
        List<String> remainingValues = filterForAntiModeAtCharacter(input, 0);
        int index = 1;
        while (remainingValues.size() > 1) {
            remainingValues = filterForAntiModeAtCharacter(remainingValues, index);
            index++;
        }
        return remainingValues.get(0);
    }

    public String getGammaRate(List<String> input) {
        final Map<Integer, List<Integer>> charactersByPosition = getCharactersByPosition(input);
        StringBuilder sb = new StringBuilder();
        charactersByPosition.forEach((key, value) -> sb.append(findMode(value)));
        return sb.toString();
    }

    public String getEpsilonRate(List<String> input) {
        final Map<Integer, List<Integer>> charactersByPosition = getCharactersByPosition(input);
        StringBuilder sb = new StringBuilder();
        charactersByPosition.forEach((key, value) -> sb.append(findAntiMode(value)));
        return sb.toString();
    }

    public int binaryConverter(String digits) {
        return Integer.parseInt(digits, 2);
    }

    private static List<String> filterForModeAtCharacter(List<String> list, int index) {
        final Map<Integer, List<Integer>> charactersByPosition = getCharactersByPosition(list);
        return list.stream()
                .filter(item -> item.split("")[index].equals(findMode(charactersByPosition.get(index)).toString()))
                .collect(Collectors.toList());
    }

    private static List<String> filterForAntiModeAtCharacter(List<String> list, int index) {
        final Map<Integer, List<Integer>> charactersByPosition = getCharactersByPosition(list);
        return list.stream()
                .filter(item -> item.split("")[index].equals(findAntiMode(charactersByPosition.get(index)).toString()))
                .collect(Collectors.toList());
    }

    private static Map<Integer, List<Integer>> getCharactersByPosition(List<String> input) {
        Map<Integer, List<Integer>> charactersByPosition = new HashMap<>();
        input.forEach(line -> {
            String[] digits = line.split("");
            IntStream.range(0, digits.length)
                    .forEach(index ->
                            charactersByPosition.computeIfAbsent(index, k -> new ArrayList<>()).add(Integer.parseInt(digits[index]))
                    );
        });
        return charactersByPosition;
    }

    private static Integer findMode(List<Integer> list) {
        int zerosCount = 0;
        int onesCount = 0;
        for (Integer integer : list) {
            if (integer == 1) {
                onesCount++;
            } else if (integer == 0) {
                zerosCount++;
            }
        }
        if (zerosCount > onesCount) {
            return 0;
        } else {
            return 1;
        }
    }

    private static Integer findAntiMode(List<Integer> list) {
        Integer mode = findMode(list);
        if (mode == 1) {
            return 0;
        } else {
            return 1;
        }
    }
}
