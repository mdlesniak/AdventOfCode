package com.magicalpoet.advent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day4GiantSquidTest {
    private static final String TEST_NUMBERS = "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1";
    private static final String REAL_NUMBERS = "4,75,74,31,76,79,27,19,69,46,98,59,83,23,90,52,87,6,11,92,80,51,43,5,94,17,15,67,25,30,48,47,62,71,85,58,60,1,72,99,3,35,42,10,96,49,37,36,8,44,70,40,45,39,0,63,2,78,68,53,50,77,20,55,38,86,54,93,26,88,12,91,95,34,9,14,33,66,41,13,28,57,29,73,56,22,89,21,64,61,32,65,97,84,18,82,81,7,16,24";
    private static final String TEST_BOARDS = "day4TestBoards.txt";
    private static final String REAL_BOARDS = "day4RealBoards.txt";
    private static final Utils utils = new Utils();
    private Day4GiantSquid giantSquid;

    @BeforeEach
    void setup() {
        giantSquid = new Day4GiantSquid();
    }

    @Test
    void createBoard() {
        String data = "22 13 17 11 0\n" +
                "8 2 23 4 24\n" +
                "21 9 14 16 7\n" +
                "6 10 3 18 5\n" +
                "1 12 20 15 19";
        BingoBoard board = giantSquid.createBoard(data);

        assertThat(board.getB1().getValue()).isEqualTo(22);
        assertThat(board.getI2().getValue()).isEqualTo(2);
        assertThat(board.getN3().getValue()).isEqualTo(14);
        assertThat(board.getG4().getValue()).isEqualTo(18);
        assertThat(board.getO5().getValue()).isEqualTo(19);

        board.getMyNumbers().forEach(number -> assertThat(number.getMarked()).isFalse());
    }

    @Test
    void getBoards() throws Exception {
        String boardsData = utils.readFileToString(TEST_BOARDS);
        List<BingoBoard> boards = giantSquid.getBoards(boardsData);
        assertThat(boards).hasSize(3);
    }

    @Test
    void bingoBoard_canMarkNumber() {
        String data = "22 13 17 11 0\n" +
                "8 2 23 4 24\n" +
                "21 9 14 16 7\n" +
                "6 10 3 18 5\n" +
                "1 12 20 15 19";
        BingoBoard board = giantSquid.createBoard(data);
        boolean bingo = board.markNumberAndAnnounceIfBingo(23);
        assertThat(board.getN2().getMarked()).isTrue();
        assertThat(bingo).isFalse();
    }

    @Test
    void bingoBoard_announcesBingoOnRow() {
        String data = "22 13 17 11 0\n" +
                "8 2 23 4 24\n" +
                "21 9 14 16 7\n" +
                "6 10 3 18 5\n" +
                "1 12 20 15 19";
        BingoBoard board = giantSquid.createBoard(data);
        board.getB1().setMarked(true);
        board.getI1().setMarked(true);
        board.getN1().setMarked(true);
        board.getG1().setMarked(true);

        Boolean bingo = board.markNumberAndAnnounceIfBingo(0);

        assertThat(bingo).isTrue();
    }

    @Test
    void bingoBoard_announcesBingoOnColumn() {
        String data = "22 13 17 11 0\n" +
                "8 2 23 4 24\n" +
                "21 9 14 16 7\n" +
                "6 10 3 18 5\n" +
                "1 12 20 15 19";
        BingoBoard board = giantSquid.createBoard(data);
        board.getB1().setMarked(true);
        board.getB2().setMarked(true);
        board.getB3().setMarked(true);
        board.getB4().setMarked(true);

        Boolean bingo = board.markNumberAndAnnounceIfBingo(1);

        assertThat(bingo).isTrue();
    }



    @Test
    void testInput() throws Exception {
        String boardsData = utils.readFileToString(TEST_BOARDS);
        int result = giantSquid.playBingo(TEST_NUMBERS, boardsData);
        assertThat(result).isEqualTo(4512);
    }

    @Test
    void partOneAnswer() throws Exception {
        String boardsData = utils.readFileToString(REAL_BOARDS);
        int result = giantSquid.playBingo(REAL_NUMBERS, boardsData);
        assertThat(result).isEqualTo(38913);
    }
}
