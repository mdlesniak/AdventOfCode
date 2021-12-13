package com.magicalpoet.advent;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day7WhalesTest {
    private static String TEST_INPUT;
    private static String REAL_INPUT;
    private static final TestUtils utils = new TestUtils();
    private Day7Whales whales;

    @BeforeAll
    static void init() throws Exception {
        TEST_INPUT = utils.readFileToString("day7TestInput.txt");
        REAL_INPUT = utils.readFileToString("day7Input.txt");
    }

    @BeforeEach
    void setup() {
        whales = new Day7Whales();
    }

    @Test
    void partOne_testInput() {
        final int fuelCost = whales.findOptimizedFuelCostEqualized(TEST_INPUT);
        assertThat(fuelCost).isEqualTo(37);
    }

    @Test
    void partOne_realInput() {
        final int fuelCost = whales.findOptimizedFuelCostEqualized(REAL_INPUT);
        assertThat(fuelCost).isEqualTo(357353);
    }

    @Test
    void partTwo_testInput() {
        final int fuelCost = whales.findOptimizedFuelCostLinearGrowth(TEST_INPUT);
        assertThat(fuelCost).isEqualTo(168);
    }

    @Test
    void partTwo_realInput() {
        final int fuelCost = whales.findOptimizedFuelCostLinearGrowth(REAL_INPUT);
        assertThat(fuelCost).isEqualTo(104822130);
    }

}
