package com.magicalpoet.advent.twentytwo;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day05SupplyStacksTest {
    private final TestUtils utils = new TestUtils();
    private Day05SupplyStacks supplyStacks;
    private String testInput;
    private String realInput;

    @BeforeEach
    void setUp() throws Exception {
        supplyStacks = new Day05SupplyStacks();
    }

    @Test
    void partOne_sampleInput() {
        assertThat(supplyStacks.getTopCrates(testInput)).isEqualTo("CMD");
    }

}
