package com.magicalpoet.advent.twentyone;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day3BinaryDiagnosticTest {
    private static final String TEST_INPUT_FILE = "2021/day3TestInput.txt";
    private static final String INPUT_FILE = "2021/day3Input.txt";
    private final TestUtils utils = new TestUtils();
    private Day3BinaryDiagnostic binaryDiagnostic;

    @BeforeEach
    void setup() {
        binaryDiagnostic = new Day3BinaryDiagnostic();
    }

    @Test
    void getGammaRate_usesMostCommonBits() throws Exception {
        List<String> input = utils.readFileToList(TEST_INPUT_FILE);
        String gammaRate = binaryDiagnostic.getGammaRate(input);
        assertThat(gammaRate).isEqualTo("10110");
    }

    @Test
    void getEpsilonRate_usesLeastCommonBits() throws Exception {
        List<String> input = utils.readFileToList(TEST_INPUT_FILE);
        String epsilonRate = binaryDiagnostic.getEpsilonRate(input);
        assertThat(epsilonRate).isEqualTo("01001");
    }

    @Test
    void binaryConverter_works() {
        assertThat((Integer) binaryDiagnostic.binaryConverter("01001")).isEqualTo(9);
        assertThat((Integer) binaryDiagnostic.binaryConverter("10110")).isEqualTo(22);
    }

    @Test
    void getOxygenRating() throws Exception {
        List<String> input = utils.readFileToList(TEST_INPUT_FILE);
        final String oxygenRating = binaryDiagnostic.getOxygenRating(input);
        assertThat(oxygenRating).isEqualTo("10111");
    }

    @Test
    void getCarbonRating() throws Exception {
        List<String> input = utils.readFileToList(TEST_INPUT_FILE);
        final String oxygenRating = binaryDiagnostic.getCarbonRating(input);
        assertThat(oxygenRating).isEqualTo("01010");
    }

    @Test
    void powerRating() throws Exception {
        List<String> input = utils.readFileToList(TEST_INPUT_FILE);
        final int powerConsumption = binaryDiagnostic.calculatePowerConsumption(input);
        assertThat(powerConsumption).isEqualTo(198);
    }

    @Test
    void lifeSupportRating() throws Exception {
        List<String> input = utils.readFileToList(TEST_INPUT_FILE);
        final int lifeSupportRating = binaryDiagnostic.calculateLifeSupportRating(input);
        assertThat(lifeSupportRating).isEqualTo(230);
    }

    @Test
    void partOneAnswer() throws Exception {
        List<String> input = utils.readFileToList(INPUT_FILE);
        int powerConsumption = binaryDiagnostic.calculatePowerConsumption(input);
        assertThat(powerConsumption).isEqualTo(845186);
    }

    @Test
    void partTwoAnswer() throws Exception {
        List<String> input = utils.readFileToList(INPUT_FILE);
        int lifeSupportRating = binaryDiagnostic.calculateLifeSupportRating(input);
        assertThat(lifeSupportRating).isEqualTo(4636702);
    }
}
