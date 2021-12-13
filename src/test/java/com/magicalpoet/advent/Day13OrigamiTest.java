package com.magicalpoet.advent;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Day13OrigamiTest {
    private static final TestUtils utils = new TestUtils();
    private static String TEST_INPUT = "";
    private static String REAL_INPUT = "";
    private static String TEST_FOLDS = "";
    private static String REAL_FOLDS = "";
    private Day13Origami origami;

    @BeforeAll
    static void readFiles() throws Exception {
        TEST_INPUT = utils.readFileToString("day13TestInput.txt");
        REAL_INPUT = utils.readFileToString("day13Input.txt");
        TEST_FOLDS = "fold along y=7\nfold along x=5";
        REAL_FOLDS = utils.readFileToString("day13FoldInstructions.txt");
    }

    @BeforeEach
    void setup() {
        origami = new Day13Origami();
    }

    @Test
    void countTestInput_singleFold() {
        int totalDots = origami.countAfterFolding(TEST_INPUT, "fold along y=7");
        assertThat(totalDots).isEqualTo(17);
    }

    @Test
    void countRealInput_singleFold() {
        int totalDots = origami.countAfterFolding(REAL_INPUT, "fold along x=655");
        assertThat(totalDots).isEqualTo(837);
    }

    @Test
    void realFoldedPaper_allFolds() {
        final Map<String, Day13Origami.Coordinate> paper = origami.createPaperAndFold(REAL_INPUT, REAL_FOLDS);
        assertThat(paper.size()).isPositive();
    }

    @Test
    void countTestInput_multipleFolds() {
        int totalDots = origami.countAfterFolding(TEST_INPUT, TEST_FOLDS);
        assertThat(totalDots).isEqualTo(16);
    }

    @Test
    void printFinalPaper() {
        String picture = origami.visualizeAfterFolding(REAL_INPUT, REAL_FOLDS);
        assertThat(picture).isNotBlank();
        System.out.println(picture);
    }
}