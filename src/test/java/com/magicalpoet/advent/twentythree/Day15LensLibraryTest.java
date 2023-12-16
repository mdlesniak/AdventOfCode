package com.magicalpoet.advent.twentythree;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Day15LensLibraryTest {
    private static final String testData = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7";
    private final TestUtils utils = new TestUtils();
    private String data;
    private Day15LensLibrary day15LensLibrary;

    @BeforeEach
    void setup() throws Exception {
        data = utils.readFileToString("2023/day15Input.txt");
        day15LensLibrary = new Day15LensLibrary();
    }

    @Test
    void canGetHashScore() {
        assertThat(day15LensLibrary.getHashScore("HASH")).isEqualTo(52);
        assertThat(day15LensLibrary.getHashScore("rn=1")).isEqualTo(30);
        assertThat(day15LensLibrary.getHashScore("rn")).isEqualTo(0);
        assertThat(day15LensLibrary.getHashScore("cm-")).isEqualTo(253);
        assertThat(day15LensLibrary.getHashScore("pc=4")).isEqualTo(180);
        assertThat(day15LensLibrary.getHashScore("ot=9")).isEqualTo(9);
    }

    @Test
    void canTotalScoresInSteps() {
        int score = day15LensLibrary.totalHashScores(testData);
        assertThat(score).isEqualTo(1320);
    }

    @Test
    void partOne() {
        int score = day15LensLibrary.totalHashScores(data);
        assertThat(score).isEqualTo(505459);
    }

    @Test
    void canProcessStep_equals() {
        day15LensLibrary.processStepEquals("qp=3");
        final Map<Integer, List<Map<String, Integer>>> boxes = day15LensLibrary.getBoxes();
        assertThat(boxes).hasSize(1);
        assertThat(boxes.get(1)).containsExactly(Map.of("qp", 3));
    }

 }
