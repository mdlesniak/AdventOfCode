package com.magicalpoet.advent.twentytwo;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day01CalorieCountingTest {
    private final TestUtils utils = new TestUtils();
    private Day01CalorieCounting calorieCounting;

    @BeforeEach
    void setUp() {
        calorieCounting = new Day01CalorieCounting();
    }

    @Test
    void canTotalBundleCalories() {
        String input = "7000\n" +
                "8000\n" +
                "9000";
        assertThat(calorieCounting.totalCaloriesInBundle(input)).isEqualTo(24000);
    }

    @Test
    void canFindGreatestTotalInBundles() {
        String input = "1000\n" +
                "2000\n" +
                "3000\n" +
                "\n" +
                "4000";
        assertThat(calorieCounting.findGreatestBundleCalories(input)).isEqualTo(6000);
    }

    @Test
    void testInputPartOne() throws Exception {
        String input = utils.readFileToString("2022/day01TestInput.txt");
        assertThat(calorieCounting.findGreatestBundleCalories(input)).isEqualTo(24000);
    }

    @Test
    void testAnswerPartOne() throws Exception {
        String input = utils.readFileToString("2022/day01Input.txt");
        int greatestBundleCalories = calorieCounting.findGreatestBundleCalories(input);
        assertThat(greatestBundleCalories).isEqualTo(70369);
    }

    @Test
    void testInputPartTwo() throws Exception {
        String input = utils.readFileToString("2022/day01TestInput.txt");
        int topThreeBundlesCalories = calorieCounting.findTopThreeBundlesTotal(input);
        assertThat(topThreeBundlesCalories).isEqualTo(45000);
    }

    @Test
    void testInputPartAnswer() throws Exception {
        String input = utils.readFileToString("2022/day01Input.txt");
        int topThreeBundlesCalories = calorieCounting.findTopThreeBundlesTotal(input);
        assertThat(topThreeBundlesCalories).isEqualTo(203002);
    }
}
