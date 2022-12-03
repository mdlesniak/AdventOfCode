package com.magicalpoet.advent.twentytwo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day03Rucksack {
    public int totalBadgePriority(String input) {
        int totalPriority = 0;
        List<String> elves = new java.util.ArrayList<>(Arrays.stream(input.split("\n")).toList());
        while (!elves.isEmpty()) {
            totalPriority += findBadgePriority(elves.remove(0), elves.remove(0), elves.remove(0));
        }
        return totalPriority;
    }

    private int findBadgePriority(String firstElf, String secondElf, String thirdElf) {
        return Arrays.stream(firstElf.split(""))
                .filter(item -> secondElf.contains(item) && thirdElf.contains(item))
                .findFirst()
                .map(this::getPriority)
                .orElse(0);
    }

    public int totalPriorityOfSacks(String input) {
        String[] sacks = input.split("\n");
        return Arrays.stream(sacks).mapToInt(
                sack -> getPriority(findDuplicateInSack(splitSack(sack)))
        ).sum();
    }

    public String[] splitSack(String sack) {
        final int mid = sack.length() / 2;
        return new String[]{sack.substring(0, mid), sack.substring(mid)};
    }

    public String findDuplicateInSack(String[] sackCompartments) {
        List<String> items = Arrays.stream(sackCompartments[0].split("")).collect(Collectors.toList());
        String duplicate = "";
        for (String item : sackCompartments[1].split("")) {
            if (items.contains(item)) {
                duplicate = item;
            }
        }

        return duplicate;
    }

    public int getPriority(String item) {
        char character = item.charAt(0);
        if (item.matches("[a-z]")) {
            return character - 96;
        }
        return character - 38;
    }
}
