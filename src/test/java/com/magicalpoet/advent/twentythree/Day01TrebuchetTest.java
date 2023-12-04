package com.magicalpoet.advent.twentythree;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day01TrebuchetTest {
    private final TestUtils utils = new TestUtils();
    private String data;
    private Day01Trebuchet trebuchet;

    @BeforeEach
    void setup() throws Exception {
        data = utils.readFileToString("2023/day01Input.txt");
        trebuchet = new Day01Trebuchet();
    }

    @Test
    void getCoordinate() {
        assertThat(trebuchet.getCoordinate("1abc2")).isEqualTo(12);
        assertThat(trebuchet.getCoordinate("a1b2c3d4e5f")).isEqualTo(15);
        assertThat(trebuchet.getCoordinate("treb7uchet")).isEqualTo(77);
    }

    @Test
    void getTotalTestData() {
        String testData = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet""";
        final long total = trebuchet.getTotalPartOne(testData);
        assertThat(total).isEqualTo(142);
    }

    @Test
    void getTotalPartOne() {
        final long total = trebuchet.getTotalPartOne(data);
        assertThat(total).isEqualTo(54331);
    }

    @Test
    void getTotalPartTwoTest() {
        String input = """
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen""";
        final long total = trebuchet.getTotalPartTwo(input);
        assertThat(total).isEqualTo(281);
    }

    @Test
    void getTotalPartTwo() {
        final long total = trebuchet.getTotalPartTwo(data);
        assertThat(total).isEqualTo(54518);
    }
}
