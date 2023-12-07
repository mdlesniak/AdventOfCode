package com.magicalpoet.advent.twentythree;

import java.util.Map;

public class Day06Races {
    public int scoreRaces(Map<Integer, Integer> race) {
        int totalWays = 1;
        for (var entry : race.entrySet()) {
            totalWays = totalWays * waysToWin(entry.getKey(), entry.getValue());
        }
        return totalWays;
    }

    public int waysToWin(Integer time, Integer recordDistance) {
        int holdTime = time / 2;
        int waysToWin = 0;
        while (holdTime > 0) {
            int distanceTraveled = holdTime * (time - holdTime);
            if (distanceTraveled > recordDistance) {
                waysToWin++;
                holdTime--;
            } else {
                break;
            }
        }
        if (time % 2 == 0) {
            return waysToWin * 2 - 1;
        } else {
            return waysToWin * 2;
        }
    }
}
