package com.magicalpoet.advent.twentytwo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day01CalorieCounting {

    public int totalCaloriesInBundle(String bundle) {
        String[] items = bundle.split("\n");
        int[] ints = Arrays.stream(items).mapToInt(Integer::parseInt).toArray();
        return Arrays.stream(ints).sum();
    }

    public int findGreatestBundleCalories(String input) {
        List<String> bundles = Arrays.stream(input.split("\n\n")).toList();
        return Collections.max(bundles.stream().map(this::totalCaloriesInBundle).toList());
    }

    public int findTopThreeBundlesTotal(String input) {
        List<String> bundles = Arrays.stream(input.split("\n\n")).collect(Collectors.toList());
        List<Integer> bundleTotals = bundles.stream()
                .map(this::totalCaloriesInBundle)
                .sorted(Comparator.reverseOrder())
                .toList();
        return bundleTotals.get(0) + bundleTotals.get(1) + bundleTotals.get(2);
    }
}
