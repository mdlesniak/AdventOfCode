package com.magicalpoet.advent;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {
    private Day2PartOne partOne;
    private Day2PartTwo partTwo;

    @BeforeEach
    void setUp() {
        partOne = new Day2PartOne();
        partTwo = new Day2PartTwo();
    }

    @Test
    void onlyOneDirection() {
        assertThat(partOne.findSubPosition(List.of("forward 4"))).isZero();
        assertThat(partOne.findSubPosition(List.of("down 3"))).isZero();
        assertThat(partOne.findSubPosition(List.of("up 6"))).isZero();
    }

    @Test
    void allThreeDirections() {
        List<String> input = List.of("down 4", "up 1", "forward 7");
        int answer = partOne.findSubPosition(input);
        assertThat(answer).isEqualTo(21);
    }

    @Test
    void partOneGivenTest() {
        List<String> input = List.of("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");
        int answer = partOne.findSubPosition(input);
        assertThat(answer).isEqualTo(150);
    }

    @Test
    void partTwoGivenTest() {
        List<String> input = List.of("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");
        int answer = partTwo.findSubPosition(input);
        assertThat(answer).isEqualTo(900);
    }

    @Test
    void partOneAnswer() throws Exception {
        List<String> input = IOUtils.readLines(this.getClass().getClassLoader().getResourceAsStream("day2Input.txt"), "utf-8");
        int answer = partOne.findSubPosition(input);
        assertThat(answer).isGreaterThan(1);
        System.out.println(answer);
    }

    @Test
    void parTwoAnswer() throws Exception {
        List<String> input = IOUtils.readLines(this.getClass().getClassLoader().getResourceAsStream("day2Input.txt"), "utf-8");
        int answer = partTwo.findSubPosition(input);
        assertThat(answer).isGreaterThan(1);
        System.out.println(answer);
    }
}
