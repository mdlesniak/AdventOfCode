package com.magicalpoet.advent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Day6LanternfishTest {
    private static final String TEST_INPUT = "3,4,3,1,2";
    private static final String REAL_INPUT = "3,3,5,1,1,3,4,2,3,4,3,1,1,3,3,1,5,4,4,1,4,1,1,1,3,3,2,3,3,4,2,5,1,4,1,2,2,4,2,5,1,2,2,1,1,1,1,4,5,4,3,1,4,4,4,5,1,1,4,3,4,2,1,1,1,1,5,2,1,4,2,4,2,5,5,5,3,3,5,4,5,1,1,5,5,5,2,1,3,1,1,2,2,2,2,1,1,2,1,5,1,2,1,2,5,5,2,1,1,4,2,1,4,2,1,1,1,4,2,5,1,5,1,1,3,1,4,3,1,3,2,1,3,1,4,1,2,1,5,1,2,1,4,4,1,3,1,1,1,1,1,5,2,1,5,5,5,3,3,1,2,4,3,2,2,2,2,2,4,3,4,4,4,1,2,2,3,1,1,4,1,1,1,2,1,4,2,1,2,1,1,2,1,5,1,1,3,1,4,3,2,1,1,1,5,4,1,2,5,2,2,1,1,1,1,2,3,3,2,5,1,2,1,2,3,4,3,2,1,1,2,4,3,3,1,1,2,5,1,3,3,4,2,3,1,2,1,4,3,2,2,1,1,2,1,4,2,4,1,4,1,4,4,1,4,4,5,4,1,1,1,3,1,1,1,4,3,5,1,1,1,3,4,1,1,4,3,1,4,1,1,5,1,2,2,5,5,2,1,5";
    private Day6Lanternfish lanternfish;

    @BeforeEach
    void setup() {
        lanternfish = new Day6Lanternfish();
    }

    @Test
    void testInput_18Days() {
        final BigInteger totalFish = lanternfish.reproduce(TEST_INPUT, 18);
        assertThat(totalFish.intValue()).isEqualTo(26);
    }

    @Test
    void testInput_80Days() {
        final BigInteger totalFish = lanternfish.reproduce(TEST_INPUT, 80);
        assertThat(totalFish.intValue()).isEqualTo(5934);
    }

    @Test
    void partOneAnswer() {
        final BigInteger totalFish = lanternfish.reproduce(REAL_INPUT, 80);
        assertThat(totalFish.intValue()).isEqualTo(362740);
    }

    @Test
    void partTwoTest() {
        final BigInteger totalFish = lanternfish.reproduce(TEST_INPUT, 256);
        assertThat(totalFish).isEqualTo(BigInteger.valueOf(26984457539L));
    }

    @Test
    void partTwoAnswer() {
        final BigInteger totalFish = lanternfish.reproduce(REAL_INPUT, 256);
        assertThat(totalFish).isEqualTo(BigInteger.valueOf(1644874076764L));
    }
}