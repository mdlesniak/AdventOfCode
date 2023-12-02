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

    public long getTotalPartOne(String data) {
        final String[] lines = data.split("\n");
        return Arrays.stream(lines).map(
                this::getCoordinateDigitsOnly
        ).mapToLong(Long::longValue).sum();
    }

    public long getTotalPartTwo(String data) {
        final String[] lines = data.split("\n");
        return Arrays.stream(lines).map(
                this::getCoordinateDigitsAndWords
        ).mapToLong(Long::longValue).sum();
    }

    public long getCoordinateDigitsAndWords(String line) {
        Matcher matcher = Pattern.compile(
                "(?=(\\d)|(one)|(two)|(three)|(four)|(five)|(six)|(seven)|(eight)|(nine))"
        ).matcher(line);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return getNumberFromFirstAndLastMatchesWithWords(result);
    }

    public long getCoordinateDigitsOnly(String line) {
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

    private static long getNumberFromFirstAndLastMatchesWithWords(List<String> matches) {
        final String first = matches.get(0);
        final String last = matches.get(matches.size() - 1);
        return Long.parseLong(translate(first) + translate(last));
    }

    private static String translate(String candidate) {
        return wordMap.getOrDefault(candidate, candidate);
    }

}
