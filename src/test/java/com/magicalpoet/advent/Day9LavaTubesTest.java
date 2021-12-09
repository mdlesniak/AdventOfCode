package com.magicalpoet.advent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Day9LavaTubesTest {

    private static final String TEST_INPUT_FILE = "day9TestInput.txt";
    private static final String INPUT_FILE = "day9Input.txt";
    private final Utils utils = new Utils();
    private Day9LavaTubes lavaTubes;

    @BeforeEach
    void setup() {
        lavaTubes = new Day9LavaTubes();
    }

    @Test
    void calculatesRiskLevel() {
        List<Integer> input = List.of(2,6,9,0,7);
        int riskLevel = lavaTubes.calculateRiskLevel(input);
        assertThat(riskLevel).isEqualTo(29);
    }

    @Test
    void isLowPoint() {
        assertThat(lavaTubes.isLowPoint(List.of(2,3), 3)).isFalse();
        assertThat(lavaTubes.isLowPoint(List.of(5,6), 3)).isTrue();
    }

    @Test
    void findsLowPoints() {
        List<Integer> row1 = List.of(3, 6, 8, 9);
        List<Integer> row2 = List.of(9, 3, 6, 4);
        List<Integer> row3 = List.of(2, 3, 7, 5);
        Map<Integer, List<Integer>> cave = new HashMap<>();
        cave.put(0, row1);
        cave.put(1, row2);
        cave.put(2, row3);

        final List<Integer> lowPoints = lavaTubes.findLowPoints(cave);

        assertThat(lowPoints).containsExactlyInAnyOrder(3, 4, 2);
    }

    @Test
    void partOneTestInput() throws Exception {
        String caveMap = utils.readFileToString(TEST_INPUT_FILE);
        final int riskLevel = lavaTubes.reportRiskLevel(caveMap);
        assertThat(riskLevel).isEqualTo(15);
    }

    @Test
    void partOneAnswer() throws Exception {
        String caveMap = utils.readFileToString(INPUT_FILE);
        final int riskLevel = lavaTubes.reportRiskLevel(caveMap);
        assertThat(riskLevel).isEqualTo(550);
    }

    @Test
    void partTwoTestInput() throws Exception {
        String caveMap = utils.readFileToString(TEST_INPUT_FILE);
        final int basinScore = lavaTubes.findBasinsScore(caveMap);
        assertThat(basinScore).isEqualTo(1134);
    }
}
