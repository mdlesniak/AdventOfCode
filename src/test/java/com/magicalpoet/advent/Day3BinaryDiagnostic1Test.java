package com.magicalpoet.advent;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day3BinaryDiagnostic1Test {
    private Day3BinaryDiagnostic1 binaryDiagnostic1;

    @BeforeEach
    void setup() {
        binaryDiagnostic1 = new Day3BinaryDiagnostic1();
    }

    @Test
    void getGammaRate_usesMostCommonBits() throws Exception {
        List<String> input = IOUtils.readLines(this.getClass().getClassLoader().getResourceAsStream("day3TestInput.txt"), "utf-8");
        String gammaRate = binaryDiagnostic1.getGammaRate(input);
        assertThat(gammaRate).isEqualTo("101100000000");
    }

    @Test
    void getEpsilonRate_usesLeastCommonBits() throws Exception {
        List<String> input = IOUtils.readLines(this.getClass().getClassLoader().getResourceAsStream("day3TestInput.txt"), "utf-8");
        String gammaRate = binaryDiagnostic1.getEpsilonRate(input);
        assertThat(gammaRate).isEqualTo("010011111111");
    }

    @Test
    void binaryConverter_works() {
        assertThat((Integer) binaryDiagnostic1.binaryConverter("01001")).isEqualTo(9);
        assertThat((Integer) binaryDiagnostic1.binaryConverter("10110")).isEqualTo(22);
    }

    @Test
    void day3PartOneAnswer() throws Exception {
        List<String> input = IOUtils.readLines(this.getClass().getClassLoader().getResourceAsStream("day3Input.txt"), "utf-8");
        int powerConsumption = binaryDiagnostic1.calculatePowerConsumption(input);
        assertThat(powerConsumption).isPositive();
        System.out.println(powerConsumption);
    }
}
