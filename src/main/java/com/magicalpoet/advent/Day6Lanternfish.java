package com.magicalpoet.advent;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Day6Lanternfish {
    public BigInteger reproduce(String fishInput, int days) {
        final String[] separatedFish = fishInput.split(",");
        Map<Integer, BigInteger> fish = new HashMap<>();

        // TODO: maybe use stream api and/or compute if absent?
        for (String input : separatedFish) {
            final Integer fishAge = Integer.valueOf(input);
            addFish(fish, fishAge, BigInteger.valueOf(1));
        }
        for (int i = 1; i <= days; i++) {
            fish = ageFish(fish);
        }

        return fish.values().stream().reduce(BigInteger.ZERO, BigInteger::add);
    }

    // TODO: could likely use stream api here and/or compute if absent here too
    private static Map<Integer, BigInteger> ageFish(Map<Integer, BigInteger> startingFish) {
        Map<Integer, BigInteger> newFish = new HashMap<>();
        startingFish.forEach((key, value) -> {
            if (key.equals(0)) {
                addResetAdults(newFish, value);
                addBabyFish(newFish, value);
            } else {
                addFish(newFish, key - 1, value);
            }
        });
        return newFish;
    }

    private static void addBabyFish(Map<Integer, BigInteger> newFish, BigInteger count) {
        addFish(newFish, 8, count);
    }

    private static void addResetAdults(Map<Integer, BigInteger> newFish, BigInteger count) {
        addFish(newFish, 6, count);
    }

    private static void addFish(Map<Integer, BigInteger> newFish, Integer daysToReproduce, BigInteger count) {
        if (newFish.containsKey(daysToReproduce)) {
            newFish.put(daysToReproduce, newFish.get(daysToReproduce).add(count));
        } else {
            newFish.put(daysToReproduce, count);
        }
    }
}
