package com.magicalpoet.advent.twentythree;

import java.util.Map;

public class Day06Races {
    public long scoreRaces(Map<Long, Long> race) {
        long totalWays = 1;
        for (var entry : race.entrySet()) {
            totalWays = totalWays * waysToWin(entry.getKey(), entry.getValue());
        }
        return totalWays;
    }

    public long waysToWin(Long time, Long recordDistance) {
        long holdTime = time / 2;
        long waysToWin = 0;
        while (holdTime > 0) {
            long distanceTraveled = holdTime * (time - holdTime);
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
