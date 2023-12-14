package com.magicalpoet.advent.twentythree;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day13MirrorsTest {
    private static final String testData = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.

            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#""";
    private final TestUtils utils = new TestUtils();
    private Day13Mirrors day13Mirrors;
    private String data;


    @BeforeEach
    void setup() throws Exception {
        day13Mirrors = new Day13Mirrors();
        data = utils.readFileToString("2023/day13Input.txt");
    }

    @Test
    void canSummarizeNotes() {
        List<Integer> columns = List.of(5);
        List<Integer> rows = List.of(4);
        assertThat(day13Mirrors.summarize(columns, rows)).isEqualTo(405);
    }

    @Test
    void canFindVerticalReflection() {
        final Day13Mirrors.Board board1 = new Day13Mirrors.Board(testData.split("\\n\\n")[0]);
        final Day13Mirrors.Board board2 = new Day13Mirrors.Board(testData.split("\\n\\n")[1]);
        final Day13Mirrors.Board board3 = new Day13Mirrors.Board("""
                .#.##.#.
                #......#
                #......#
                .#.##.#.""");

        assertThat(board1.getVerticalReflection()).isEqualTo(5);
        assertThat(board2.getVerticalReflection()).isZero();
        assertThat(board3.getVerticalReflection()).isEqualTo(4);
    }

    @Test
    void canFindVerticalReflectionNotNearCenter() {
        final Day13Mirrors.Board board = new Day13Mirrors.Board("""
                .#.#.####
                .###.#..#
                #..#.....
                ..#...##.
                ..#.##..#
                .#.#.....
                ...#.####
                ###......
                ...###..#
                ...###..#
                .##......""");
        assertThat(board.getVerticalReflection()).isEqualTo(7);
    }

    @Test
    void canFindHorizontalReflection() {
        final Day13Mirrors.Board board1 = new Day13Mirrors.Board(testData.split("\\n\\n")[0]);
        final Day13Mirrors.Board board2 = new Day13Mirrors.Board(testData.split("\\n\\n")[1]);
        final Day13Mirrors.Board board3 = new Day13Mirrors.Board("""
                #.##..##.
                ..##..##.
                ..#.##.#.
                ##......#
                ##......#
                ..#.##.#.
                ..##..##.
                #.##..##.""");

        assertThat(board1.getHorizontalReflection()).isZero();
        assertThat(board2.getHorizontalReflection()).isEqualTo(4);
        assertThat(board3.getHorizontalReflection()).isEqualTo(4);
    }

    @Test
    void canFindHorizontalReflectionNotNearCenter() {
        final Day13Mirrors.Board board = new Day13Mirrors.Board("""
                #.##..##.
                ..##..##.
                ..#.##.#.
                ##......#
                ##......#
                ..#.##.#.""");
        assertThat(board.getHorizontalReflection()).isEqualTo(4);
    }

    @Test
    void canSummarizeBoards() {
        day13Mirrors.summarize(testData.split("\\n\\n"));
        assertThat(day13Mirrors.getColumnScores()).containsExactly(5);
        assertThat(day13Mirrors.getRowScores()).containsExactly(4);
    }

    @Test
    void partOne() {
        day13Mirrors.summarize(data.split("\\n\\n"));
        final int score = day13Mirrors.summarize(day13Mirrors.getColumnScores(), day13Mirrors.getRowScores());
        assertThat(score).isEqualTo(3);
    }
}
