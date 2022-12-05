package com.magicalpoet.advent.twentytwo;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day02RockPaperScissorsTest {
    public static final String TEST_INPUT = """
            A Y
            B X
            C Z
            """;
    private final TestUtils utils = new TestUtils();
    private Day02RockPaperScissors rockPaperScissors;

    @BeforeEach
    void setUp() {
        rockPaperScissors = new Day02RockPaperScissors();
    }

    @Test
    void canScoreRound() {
        assertThat(rockPaperScissors.scoreRound("A Y")).isEqualTo(8);
        assertThat(rockPaperScissors.scoreRound("B X")).isEqualTo(1);
        assertThat(rockPaperScissors.scoreRound("C Z")).isEqualTo(6);
    }

    @Test
    void canScoreMatch() {
        assertThat(rockPaperScissors.scoreMatch(TEST_INPUT)).isEqualTo(15);
    }

    @Test
    void partOne() throws Exception {
        String input = utils.readFileToString("2022/day02Input.txt");
        assertThat(rockPaperScissors.scoreMatch(input)).isEqualTo(15632);
    }

    @Test
    void canScoreMatchSecondWay() {
        assertThat(rockPaperScissors.scoreMatchSecondWay(TEST_INPUT)).isEqualTo(12);
    }

    @Test
    void partTwo() throws Exception {
        String input = utils.readFileToString("2022/day02Input.txt");
        assertThat(rockPaperScissors.scoreMatchSecondWay(input)).isEqualTo(14416);
    }
}
