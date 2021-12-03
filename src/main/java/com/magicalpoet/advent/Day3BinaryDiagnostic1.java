package com.magicalpoet.advent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day3BinaryDiagnostic1 {
    List<Integer> firstPositions = new ArrayList<>();
    List<Integer> secondPositions = new ArrayList<>();
    List<Integer> thirdPositions = new ArrayList<>();
    List<Integer> fourthPositions = new ArrayList<>();
    List<Integer> fifthPositions = new ArrayList<>();
    List<Integer> sixthPositions = new ArrayList<>();
    List<Integer> seventhPositions = new ArrayList<>();
    List<Integer> eighthPositions = new ArrayList<>();
    List<Integer> ninthPositions = new ArrayList<>();
    List<Integer> tenthPositions = new ArrayList<>();
    List<Integer> eleventhPositions = new ArrayList<>();
    List<Integer> twelfthPositions = new ArrayList<>();

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

    public String getGammaRate(List<String> input) {
        populatePositionLists(input);

        StringBuilder sb = new StringBuilder();
        sb.append(getMode(firstPositions));
        sb.append(getMode(secondPositions));
        sb.append(getMode(thirdPositions));
        sb.append(getMode(fourthPositions));
        sb.append(getMode(fifthPositions));
        sb.append(getMode(sixthPositions));
        sb.append(getMode(seventhPositions));
        sb.append(getMode(eighthPositions));
        sb.append(getMode(ninthPositions));
        sb.append(getMode(tenthPositions));
        sb.append(getMode(eleventhPositions));
        sb.append(getMode(twelfthPositions));

        return sb.toString();
    }

    public String getEpsilonRate(List<String> input) {
        populatePositionLists(input);

        StringBuilder sb = new StringBuilder();
        sb.append(getAntiMode(firstPositions));
        sb.append(getAntiMode(secondPositions));
        sb.append(getAntiMode(thirdPositions));
        sb.append(getAntiMode(fourthPositions));
        sb.append(getAntiMode(fifthPositions));
        sb.append(getAntiMode(sixthPositions));
        sb.append(getAntiMode(seventhPositions));
        sb.append(getAntiMode(eighthPositions));
        sb.append(getAntiMode(ninthPositions));
        sb.append(getAntiMode(tenthPositions));
        sb.append(getAntiMode(eleventhPositions));
        sb.append(getAntiMode(twelfthPositions));

        return sb.toString();
    }

    public int binaryConverter(String digits) {
        int integer = Integer.parseInt(digits, 2);
        return integer;
    }

    private void populatePositionLists(List<String> input) {
        input.forEach(line -> {
            String[] digits = line.split("");
            try {
                firstPositions.add(Integer.parseInt(digits[0]));
                secondPositions.add(Integer.parseInt(digits[1]));
                thirdPositions.add(Integer.parseInt(digits[2]));
                fourthPositions.add(Integer.parseInt(digits[3]));
                fifthPositions.add(Integer.parseInt(digits[4]));
                sixthPositions.add(Integer.parseInt(digits[5]));
                seventhPositions.add(Integer.parseInt(digits[6]));
                eighthPositions.add(Integer.parseInt(digits[7]));
                ninthPositions.add(Integer.parseInt(digits[8]));
                tenthPositions.add(Integer.parseInt(digits[9]));
                eleventhPositions.add(Integer.parseInt(digits[10]));
                twelfthPositions.add(Integer.parseInt(digits[11]));
            } catch (IndexOutOfBoundsException e) {
                // processing smaller input for tests
            }
        });
    }

    private Integer getMode(List<Integer> list) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int max = 1;
        int temp = 0;

        for (Integer integer : list) {

            if (hm.get(integer) != null) {

                int count = hm.get(integer);
                count++;
                hm.put(integer, count);

                if (count > max) {
                    max = count;
                    temp = integer;
                }
            } else
                hm.put(integer, 1);
        }
        return temp;
    }

    private Integer getAntiMode(List<Integer> list) {
        Integer mode = getMode(list);
        if (mode == 1) {
            return 0;
        } else {
            return 1;
        }
    }
}
