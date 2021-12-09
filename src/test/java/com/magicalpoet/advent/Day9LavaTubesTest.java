package com.magicalpoet.advent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
    void calculateRiskLevel() {
        List<Integer> input = List.of(2,6,9,0,7);
        int riskLevel = lavaTubes.calculateRiskLevel(input);
        assertThat(riskLevel).isEqualTo(29);
    }

    @Test
    void partOneTestInput() throws Exception {
        String caveMap = utils.readFileToString(TEST_INPUT_FILE);
        assertThat(lavaTubes.reportRiskLevel(caveMap)).isEqualTo(15);
    }

}
