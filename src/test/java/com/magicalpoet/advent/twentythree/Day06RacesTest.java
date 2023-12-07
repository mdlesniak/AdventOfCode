package com.magicalpoet.advent.twentythree;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Day06RacesTest {
    private final Map<Integer, Integer> input = Map.of(
            47, 282,
            70, 1079,
            75, 1147,
            66, 1062
    );
    private final Day06Races races = new Day06Races();

    @Test
    void findsWaysToWin() {
        assertThat(races.waysToWin(7, 9)).isEqualTo(4);
        assertThat(races.waysToWin(15, 40)).isEqualTo(8);
        assertThat(races.waysToWin(30, 200)).isEqualTo(9);
        assertThat(races.waysToWin(71530, 940200)).isEqualTo(71503);
    }

    @Test
    void scoresRaces() {
        Map<Integer, Integer> testRaces = Map.of(
                7, 9,
                15, 40,
                30, 200
        );
        assertThat(races.scoreRaces(testRaces)).isEqualTo(288);
    }

    @Test
    void partOne() {
        assertThat(races.scoreRaces(input)).isEqualTo(281600);
    }

    @Test
    void partTwo() {
        assertThat(races.scoreRaces(Map.of(71530, 940200))).isEqualTo(71503);
    }


}
