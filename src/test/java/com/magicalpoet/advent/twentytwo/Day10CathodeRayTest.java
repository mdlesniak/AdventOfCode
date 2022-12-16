package com.magicalpoet.advent.twentytwo;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day10CathodeRayTest {
    private final TestUtils utils = new TestUtils();
    private Day10CathodeRay cathodeRay;
    private String testInput;
    private String realInput;

    @BeforeEach
    void setUp() throws Exception {
        cathodeRay = new Day10CathodeRay();
        testInput = utils.readFileToString("2022/day10TestInput.txt");
        realInput = utils.readFileToString("2022/day10Input.txt");
    }

    @Test
    void partOne_testInput() {
        List<Integer> strengths = cathodeRay.calculateSignalStrengths(testInput);
        assertThat(strengths).hasSize(6);
        assertThat(cathodeRay.totalSignalStrenths(strengths)).isEqualTo(13140);
    }

    @Test
    void partOne() {
        List<Integer> strengths = cathodeRay.calculateSignalStrengths(realInput);
        assertThat(cathodeRay.totalSignalStrenths(strengths)).isEqualTo(11960);
    }
}
