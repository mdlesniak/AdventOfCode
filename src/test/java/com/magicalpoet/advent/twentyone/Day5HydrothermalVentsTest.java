package com.magicalpoet.advent.twentyone;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day5HydrothermalVentsTest {
    private static final TestUtils utils = new TestUtils();
    private static String TEST_INPUT;
    private static String REAL_INPUT;
    private Day5HydrothermalVents vents;

    @BeforeAll
    static void init() throws Exception {
        TEST_INPUT = utils.readFileToString("2021/day5TestInput.txt");
        REAL_INPUT = utils.readFileToString("2021/day5Input.txt");
    }

    @BeforeEach
    void setup() {
        vents = new Day5HydrothermalVents();
    }

    @Test
    void partOne_testInput() {
        final int overlappingPoints = vents.countOverlappingLines(TEST_INPUT);
        assertThat(overlappingPoints).isEqualTo(5);
    }

    @Test
    void partOne_realInput() {
        final int overlappingPoints = vents.countOverlappingLines(REAL_INPUT);
        assertThat(overlappingPoints).isEqualTo(7297);
    }

    @Test
    void partTwo_testInput() {
        final int overlappingPoints = vents.countOverlappingLines(TEST_INPUT);
        assertThat(overlappingPoints).isEqualTo(12);
    }

    @Test
    void partTwo_realInput() {
        final int overlappingPoints = vents.countOverlappingLines(REAL_INPUT);
        assertThat(overlappingPoints).isEqualTo(21038);
    }
}
