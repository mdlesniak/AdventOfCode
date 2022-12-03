package com.magicalpoet.advent.twentyone;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day2DiveTest {
    private final TestUtils utils = new TestUtils();
    private Day2Dive1 partOne;
    private Day2Dive2 partTwo;

    @BeforeEach
    void setUp() {
        partOne = new Day2Dive1();
        partTwo = new Day2Dive2();
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
        List<String> input = utils.readFileToList("2021/day2Input.txt");
        int answer = partOne.findSubPosition(input);
        assertThat(answer).isGreaterThan(1);
        System.out.println(answer);
    }

    @Test
    void parTwoAnswer() throws Exception {
        List<String> input = utils.readFileToList("2021/day2Input.txt");
        int answer = partTwo.findSubPosition(input);
        assertThat(answer).isGreaterThan(1);
        System.out.println(answer);
    }
}
