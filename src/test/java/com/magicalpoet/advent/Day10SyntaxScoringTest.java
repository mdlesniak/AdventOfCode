package com.magicalpoet.advent;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class Day10SyntaxScoringTest {
    private static final TestUtils utils = new TestUtils();
    private static String TEST_DATA;
    private static String REAL_DATA;
    private Day10SyntaxScoring syntaxChecker;

    @BeforeAll
    static void readFiles() throws Exception {
        TEST_DATA = utils.readFileToString("day10TestInput.txt");
        REAL_DATA = utils.readFileToString("day10Input.txt");
    }

    @BeforeEach
    void setup() {
        syntaxChecker = new Day10SyntaxScoring();
    }

    @Test
    void syntaxChecker_identifiesCorruption() throws Exception {
        final String input = "{([(<{}[<>[]}>{[]{[(<()>";
        final String badChar = syntaxChecker.findCorruption(input);
        assertThat(badChar).isEqualTo("}");
    }

    @Test
    void syntaxChecker_isFineWithGoodLine() throws Exception {
        final String input = "{}";
        final String badChar = syntaxChecker.findCorruption(input);
        assertThat(badChar).isBlank();
    }

    @Test
    void syntaxChecker_identifiesIncompleteLine() {
        final String input = "{(";
        Throwable thrown = catchThrowable(() -> syntaxChecker.findCorruption(input));
        assertThat(thrown).isInstanceOf(Day10SyntaxScoring.IncompleteLineException.class);
    }

    @Test
    void syntaxChecker_scoresIncompleteLines() {
        final String input = "{(";
        int score = syntaxChecker.calculateCorruptionScore(input);
        assertThat(score).isZero();
    }

    @Test
    void syntaxChecker_scoresGoodLines() {
        final String input = "{()}";
        int score = syntaxChecker.calculateCorruptionScore(input);
        assertThat(score).isZero();
    }

    @Test
    void syntaxChecker_scoresBadLines() {
        assertThat(syntaxChecker.calculateCorruptionScore("<())")).isEqualTo(3);
        assertThat(syntaxChecker.calculateCorruptionScore("<(]")).isEqualTo(57);
        assertThat(syntaxChecker.calculateCorruptionScore("<(}")).isEqualTo(1197);
        assertThat(syntaxChecker.calculateCorruptionScore("<(>")).isEqualTo(25137);
    }

    @Test
    void partOneTestInput() {
        int score = syntaxChecker.calculateCorruptionScore(TEST_DATA);
        assertThat(score).isEqualTo(26397);
    }

    @Test
    void partOneAnswer() {
        int score = syntaxChecker.calculateCorruptionScore(REAL_DATA);
        assertThat(score).isEqualTo(366027);
    }

    @Test
    void canAutocomplete() {
        final String input = "[({(<(())[]>[[{[]{<()<>>";
        String completion = syntaxChecker.findCompletion(input);
        assertThat(completion).isEqualTo("}}]])})]");
    }

    @Test
    void canScoreAutocompletions() {
        assertThat(syntaxChecker.scoreAutocompletion("}}]])})]")).isEqualTo(288957);
        assertThat(syntaxChecker.scoreAutocompletion(")}>]})")).isEqualTo(5566);
        assertThat(syntaxChecker.scoreAutocompletion("}}>}>))))")).isEqualTo(1480781);
        assertThat(syntaxChecker.scoreAutocompletion("]]}}]}]}>")).isEqualTo(995444);
        assertThat(syntaxChecker.scoreAutocompletion("])}>")).isEqualTo(294);
        assertThat(syntaxChecker.scoreAutocompletion("")).isZero();
    }

    @Test
    void partTwoTestInput() {
        long score = syntaxChecker.calculateAutocompleteScore(TEST_DATA);
        assertThat(score).isEqualTo(288957);
    }

    @Test
    void partTwoTestAnswer() {
        long score = syntaxChecker.calculateAutocompleteScore(REAL_DATA);
        assertThat(score).isEqualTo(1118645287);
    }
}
