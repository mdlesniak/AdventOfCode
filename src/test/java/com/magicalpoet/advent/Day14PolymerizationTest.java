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
    void setup() throws Exception {
        polymerization = new Day14Polymerization();
    }


    @Test
    void canPolymerize() {
        Map<String, String> myRules = Map.of("NC", "B", "CB", "H");
        Map<String, Long> myTemplate = Map.of("NN", 1L, "NC", 1L, "CB", 1L); // NNCB

        final Map<String, Long> polymerMap = polymerization.polymerize(myTemplate, myRules);

        // NNBCHB
        assertThat(polymerMap).containsOnly(
                entry("NN", 1L),
                entry("NB", 1L),
                entry("BC", 1L),
                entry("CH", 1L),
                entry("HB", 1L)
        );
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

    @Test
    void partTwo_realData() {
        final long result = polymerization.process(REAL_TEMPLATE, REAL_RULES, 40);
        assertThat(result).isGreaterThan(2856661253410L);
        System.out.println(result);
    }
}
