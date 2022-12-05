package com.magicalpoet.advent.twentytwo;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day04CampCleanUpTest {
    private static final String TEST_INPUT = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8""";
    private final TestUtils utils = new TestUtils();
    private Day04CampCleanUp campCleanUp;

    @BeforeEach
    void setUp() {
        campCleanUp = new Day04CampCleanUp();
    }

    @Test
    void canDetermineFullOverlaps() {
        assertThat(campCleanUp.isFullOverlap("2-4,6-8")).isFalse();
        assertThat(campCleanUp.isFullOverlap("2-8,3-7")).isTrue();
        assertThat(campCleanUp.isFullOverlap("6-6,4-6")).isTrue();
    }

    @Test
    void canCountFullOverlaps() {
        assertThat(campCleanUp.countFullOverlaps(TEST_INPUT)).isEqualTo(2);
    }

    @Test
    void partOne() throws IOException {
        String input = utils.readFileToString("2022/day04Input.txt");
        assertThat(campCleanUp.countFullOverlaps(input)).isEqualTo(466);
    }

    @Test
    void canDeterminePartialOverlaps() {
        assertThat(campCleanUp.isPartialOverlap("2-4,6-8")).isFalse();
        assertThat(campCleanUp.isPartialOverlap("2-3,4-5")).isFalse();
        assertThat(campCleanUp.isPartialOverlap("5-7,7-9")).isTrue();
        assertThat(campCleanUp.isPartialOverlap("2-8,3-7")).isTrue();
        assertThat(campCleanUp.isPartialOverlap("6-6,4-6")).isTrue();
        assertThat(campCleanUp.isPartialOverlap("2-6,4-8")).isTrue();
    }

    @Test
    void partTwoTestInput() {
        assertThat(campCleanUp.countPartialOverlaps(TEST_INPUT)).isEqualTo(4);
    }

    @Test
    void partTwo() throws IOException {
        String input = utils.readFileToString("2022/day04Input.txt");
        assertThat(campCleanUp.countPartialOverlaps(input)).isEqualTo(865);
    }
}
