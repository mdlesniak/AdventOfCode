package com.magicalpoet.advent.twentythree;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02Cube {
    private final Pattern redPattern = Pattern.compile("(\\d+)(?: red)");
    private final Pattern bluePattern = Pattern.compile("(\\d+)(?: blue)");
    private final Pattern greenPattern = Pattern.compile("(\\d+)(?: green)");
    private final Pattern gameNumber = Pattern.compile("Game (\\d+)");
    private long totalScore = 0;

    public long totalGoodGames(String input) {
        final String[] games = input.split("\n");
        Arrays.stream(games).forEach(this::addGoodGames);
        return totalScore;
    }

    public long totalPowerSets(String input) {
        final String[] games = input.split("\n");
        for (String game : games) {
            totalScore += calculatePowerSet(game);
        }
        return totalScore;
    }

    public boolean isPossible(String game) {
        return !(redNoGood(game) | blueNoGood(game) | greenNoGood(game));
    }

    public long calculatePowerSet(String game) {
        long red = findMaximum(redPattern, game);
        long blue = findMaximum(bluePattern, game);
        long green = findMaximum(greenPattern, game);
        return red * blue * green;
    }

    private void addGoodGames(String game) {
        if (isPossible(game)) {
            final Matcher matcher = gameNumber.matcher(game);
            while (matcher.find()) {
                totalScore += Long.parseLong(matcher.group(1));
            }
        }
    }

    private long findMaximum(Pattern redPattern, String game) {
        Matcher matcher = redPattern.matcher(game);
        long max = 0;
        while (matcher.find()) {
            final long candidate = Long.parseLong(matcher.group(1));
            if (candidate > max) {
                max = candidate;
            }
        }
        return max;
    }

    private boolean redNoGood(String game) {
        return colorNoGood(redPattern, 12, game);
    }

    private boolean blueNoGood(String game) {
        return colorNoGood(bluePattern, 14, game);
    }

    private boolean greenNoGood(String game) {
        return colorNoGood(greenPattern, 13, game);
    }

    private boolean colorNoGood(Pattern colorPattern, long limit, String game) {
        final Matcher matcher = colorPattern.matcher(game);
        while (matcher.find()) {
            if (Long.parseLong(matcher.group(1)) > limit) {
                return true;
            }
        }
        return false;
    }
}
