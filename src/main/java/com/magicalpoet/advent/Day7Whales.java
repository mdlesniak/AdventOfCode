package com.magicalpoet.advent;

import java.util.Arrays;

public class Day7Whales {

    public int findOptimizedFuelCostEqualized(String testInput) {
        int[] positions = Arrays.stream(testInput.split(",")).mapToInt(Integer::parseInt).toArray();
        final int median = findMedian(positions);
        int totalFuelCost = 0;
        for (int position : positions) {
            totalFuelCost = totalFuelCost + Math.abs(position - median);
        }
        return totalFuelCost;
    }

    public int findOptimizedFuelCostLinearGrowth(String testInput) {
        int[] positions = Arrays.stream(testInput.split(",")).mapToInt(Integer::parseInt).toArray();
        final int mean = findMean(positions);
        int totalFuelCost = 0;
        for (int position : positions) {
            totalFuelCost = totalFuelCost + calculateLinearGrowthFuelCost(position, mean);
        }
        return totalFuelCost;
    }

    private static int calculateLinearGrowthFuelCost(int position, int target) {
        final double distance = Math.abs(position - target);
        final double cost = (distance / 2) * (distance + 1);
        return (int) cost;
    }

    private static int findMedian(int[] positions) {
        Arrays.sort(positions);
        return (positions[positions.length / 2] + positions[(positions.length - 1) / 2]) / 2;
    }

    private static int findMean(int[] positions) {
        final double sum = Arrays.stream(positions).sum();
        final double mean = sum / positions.length;
        return (int) mean;
    }
}
