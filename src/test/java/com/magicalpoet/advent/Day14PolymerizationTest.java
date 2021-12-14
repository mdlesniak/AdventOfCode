package com.magicalpoet.advent;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

class Day14PolymerizationTest {
    private static final String TEST_TEMPLATE = "NNCB";
    private static final String REAL_TEMPLATE = "FNFPPNKPPHSOKFFHOFOC";
    private static final TestUtils utils = new TestUtils();
    private static String TEST_RULES;
    private static String REAL_RULES;
    private Day14Polymerization polymerization;

    @BeforeAll
    static void init() throws Exception {
        TEST_RULES = utils.readFileToString("day14TestRules.txt");
        REAL_RULES = utils.readFileToString("day14Rules.txt");
    }

    @BeforeEach
    void setup() {
        polymerization = new Day14Polymerization();
    }

    @Test
    void canCountElementsInChain() {
        Map<String, Integer> result = polymerization.countElements("NBCCNBBBCBHCB");
        assertThat(result).contains(entry("N", 2), entry("B", 6), entry("C", 4));
    }

    @Test
    void canPolymerize() {
        Map<String, String> myRules = Map.of("NC", "B", "CB", "H");
        final String result = polymerization.polymerize(TEST_TEMPLATE, myRules);
        assertThat(result).isEqualTo("NNBCHB");
    }

    @Test
    void partOneTestInput_oneIteration() {
        final long result = polymerization.process(TEST_TEMPLATE, TEST_RULES, 1);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void partOneTestInput_tenIterations() {
        final long result = polymerization.process(TEST_TEMPLATE, TEST_RULES, 10);
        assertThat(result).isEqualTo(1588);
    }

    @Test
    void partOneAnswer() {
        final long result = polymerization.process(REAL_TEMPLATE, REAL_RULES, 10);
        assertThat(result).isEqualTo(2975);
    }

    @Test
    void partTwo_testData() {
        final long result = polymerization.process(TEST_TEMPLATE, TEST_RULES, 40);
        assertThat(result).isEqualTo(2188189693529L);
    }

}