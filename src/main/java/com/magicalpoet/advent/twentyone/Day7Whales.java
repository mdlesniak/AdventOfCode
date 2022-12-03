package com.magicalpoet.advent.twentyone;

import com.magicalpoet.advent.Utils;

import java.util.Arrays;

public class Day7Whales {
    public static final Utils utils = new Utils();

    public int findOptimizedFuelCostEqualized(String testInput) {
        int[] positions = Arrays.stream(testInput.split(",")).mapToInt(Integer::parseInt).toArray();
        final int median = utils.findMedian(positions);
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

    private static int findMean(int[] positions) {
        final double sum = Arrays.stream(positions).sum();
        final double mean = sum / positions.length;
        return (int) mean;
    }
}
