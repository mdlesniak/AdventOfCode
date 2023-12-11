package com.magicalpoet.advent.twentythree;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Day06RacesTest {
    private final Map<Long, Long> input = Map.of(
            47L, 282L,
            70L, 1079L,
            75L, 1147L,
            66L, 1062L
    );
    private final Day06Races races = new Day06Races();

    @Test
    void findsWaysToWin() {
        assertThat(races.waysToWin(7L, 9L)).isEqualTo(4);
        assertThat(races.waysToWin(15L, 40L)).isEqualTo(8);
        assertThat(races.waysToWin(30L, 200L)).isEqualTo(9);
        assertThat(races.waysToWin(71530L, 940200L)).isEqualTo(71503);
    }

    @Test
    void scoresRaces() {
        Map<Long, Long> testRaces = Map.of(
                7L, 9L,
                15L, 40L,
                30L, 200L
        );
        assertThat(races.scoreRaces(testRaces)).isEqualTo(288);
    }

    @Test
    void partOne() {
        assertThat(races.scoreRaces(input)).isEqualTo(281600);
    }

    @Test
    void partTwoTest() {
        assertThat(races.scoreRaces(Map.of(71530L, 940200L))).isEqualTo(71503);
    }

    @Test
    void partTwo() {
        assertThat(races.scoreRaces(Map.of(47707566L, 282107911471062L))).isEqualTo(33875953);
    }
}
