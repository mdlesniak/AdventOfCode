package com.magicalpoet.advent.twentytwo;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day12HillClimbTest {
    private final TestUtils utils = new TestUtils();
    private Day12HillClimb hillClimb;
    private String testInput;
    private String realInput;

    @BeforeEach
    void setUp() throws Exception {
        hillClimb = new Day12HillClimb();
        testInput = """
                Sabqponm
                abcryxxl
                accszExk
                acctuvwj
                abdefghi""";
        realInput = utils.readFileToString("2022/day12Input.txt");
    }

    @Test
    void partOne_sampleInput() {
        assertThat(hillClimb.climbShortest(testInput)).isEqualTo(31);
    }

    @Test
    void partOne() {
        assertThat(hillClimb.climbShortest(realInput)).isEqualTo(520);
    }

    @Test
    void partTwo_sample() {
        assertThat(hillClimb.findBestStartingPoint(testInput)).isEqualTo(29);
    }

    @Test
    void partTwo() {
        assertThat(hillClimb.findBestStartingPoint(realInput)).isEqualTo(508);
    }
}
