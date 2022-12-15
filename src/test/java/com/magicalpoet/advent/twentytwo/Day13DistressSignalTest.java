package com.magicalpoet.advent.twentytwo;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day13DistressSignalTest {
    private final TestUtils utils = new TestUtils();
    private Day13DistressSignal distressSignal;
    private String testInput;
    private String realInput;

    @BeforeEach
    void setUp() throws Exception {
        distressSignal = new Day13DistressSignal();
        testInput = utils.readFileToString("2022/day13TestInput.txt");
        realInput = utils.readFileToString("2022/day13Input.txt");
    }

    @Test
    void canCreateSimplePacket() {
        assertThat(distressSignal.createPacket("[9]")).hasSize(1);
        List<Integer> packet = distressSignal.createPacket("[1,1,3,1,1]");
        assertThat(packet).hasSize(5);
    }

    @Test
    void canCreateComplexPacket() {
        assertThat(distressSignal.createPacket("[[1],[2,3,4]]")).hasSize(2);
    }
}
