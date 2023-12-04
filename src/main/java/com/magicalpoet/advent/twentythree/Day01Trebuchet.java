package com.magicalpoet.advent.twentythree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day01Trebuchet {
    private static final Map<String, String> wordMap = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );
    private static final Map<String, String> overlappingWordMap = Map.of(
            "oneight", "18",
            "twone", "21",
            "threeight", "38",
            "fiveight", "58",
            "sevenine", "79",
            "eightwo", "82",
            "eighthree", "83",
            "nineight", "98"
    );

    public long getTotalPartOne(String data) {
        return getTotal(data);
    }

    public long getTotalPartTwo(String data) {
        return getTotal(covertWordsToDigits(data));
    }

    private static String covertWordsToDigits(String data) {
        for (var entry : overlappingWordMap.entrySet()) {
            data = data.replace(entry.getKey(), entry.getValue());
        }
        for (var entry : wordMap.entrySet()) {
            data = data.replace(entry.getKey(), entry.getValue());
        }
        return data;
    }

    private long getTotal(String data) {
        final String[] lines = data.split("\n");
        return Arrays.stream(lines).map(
                this::getCoordinate
        ).mapToLong(Long::longValue).sum();
    }

    public long getCoordinate(String line) {
        Matcher matcher = Pattern.compile("\\d").matcher(line);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return getNumberFromFirstAndLastMatches(result);
    }

    private static long getNumberFromFirstAndLastMatches(List<String> matches) {
        final String firstDigit = matches.get(0);
        final String lastDigit = matches.get(matches.size() - 1);
        return Long.parseLong(firstDigit + lastDigit);
    }
}
