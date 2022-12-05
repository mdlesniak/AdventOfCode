package com.magicalpoet.advent.twentytwo;

import java.util.Arrays;
import java.util.List;

public class Day04CampCleanUp {

    public int countPartialOverlaps(String assignmentList) {
        String[] assignments = assignmentList.split("\n");
        return Arrays.stream(assignments).filter(this::isPartialOverlap).toArray().length;
    }

    public int countFullOverlaps(String assignmentList) {
        String[] assignments = assignmentList.split("\n");
        return Arrays.stream(assignments).filter(this::isFullOverlap).toList().size();
    }

    public boolean isFullOverlap(String assignments) {
        String[] elvesSplit = assignments.split(",");
        int[] firstElf = Arrays.stream(elvesSplit[0].split("-")).mapToInt(Integer::parseInt).toArray();
        int[] secondElf = Arrays.stream(elvesSplit[1].split("-")).mapToInt(Integer::parseInt).toArray();
        return (firstElf[0] <= secondElf[0] && firstElf[1] >= secondElf[1]) ||
                (secondElf[0] <= firstElf[0] && secondElf[1] >= firstElf[1]);
    }

    public boolean isPartialOverlap(String assignments) {
        String[] elvesSplit = assignments.split(",");
        int[] firstElf = Arrays.stream(elvesSplit[0].split("-")).mapToInt(Integer::parseInt).toArray();
        int[] secondElf = Arrays.stream(elvesSplit[1].split("-")).mapToInt(Integer::parseInt).toArray();
        int lowerBound = Math.max(firstElf[0], secondElf[0]);
        int upperBound = Math.min(firstElf[1], secondElf[1]);
        return lowerBound <= upperBound;
    }
}
