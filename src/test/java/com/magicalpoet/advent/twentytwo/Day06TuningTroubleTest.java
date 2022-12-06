package com.magicalpoet.advent.twentytwo;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class Day06TuningTroubleTest {
    private final TestUtils utils = new TestUtils();
    private Day06TuningTrouble tuningTrouble;

    @BeforeEach
    void setUp() {
        tuningTrouble = new Day06TuningTrouble();
    }

    @Test
    void partOne_sampleInput() {
        assertThat(tuningTrouble.findStartOfPacket("mjqjpqmgbljsphdztnvjfqwrcgsmlb")).isEqualTo(7);
        assertThat(tuningTrouble.findStartOfPacket("bvwbjplbgvbhsrlpgdmjqwftvncz")).isEqualTo(5);
        assertThat(tuningTrouble.findStartOfPacket("nppdvjthqldpwncqszvftbrmjlhg")).isEqualTo(6);
        assertThat(tuningTrouble.findStartOfPacket("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")).isEqualTo(11);
    }

    @Test
    void partOne() throws IOException {
        String input = utils.readFileToString("2022/day06Input.txt");
        assertThat(tuningTrouble.findStartOfPacket(input)).isEqualTo(1953);
    }

    @Test
    void partTwo_sampleInput() {
        assertThat(tuningTrouble.findStartOfMessage("mjqjpqmgbljsphdztnvjfqwrcgsmlb")).isEqualTo(19);
        assertThat(tuningTrouble.findStartOfMessage("bvwbjplbgvbhsrlpgdmjqwftvncz")).isEqualTo(23);
        assertThat(tuningTrouble.findStartOfMessage("nppdvjthqldpwncqszvftbrmjlhg")).isEqualTo(23);
        assertThat(tuningTrouble.findStartOfMessage("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")).isEqualTo(26);
    }

    @Test
    void partTwo() throws IOException {
        String input = utils.readFileToString("2022/day06Input.txt");
        assertThat(tuningTrouble.findStartOfMessage(input)).isEqualTo(2301);
    }
}
