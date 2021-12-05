package com.magicalpoet.advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4GiantSquid {
    public int playBingo(String numbers, List<BingoBoard> boards) {
        return 0;
    }

    public List<BingoBoard> getBoards(String data) throws Exception {
        String[] boards = data.split("\n\r");
        return Arrays.stream(boards).map(this::createBoard).collect(Collectors.toList());
    }

    private BingoBoard createBoard(String board) {
        BingoBoard bingoBoard = new BingoBoard();
        String[] lines = board.split("\n");
        bingoBoard.populateFirstRow(lines[0].split(" "));
        bingoBoard.populateSecondRow(lines[0].split(" "));
        bingoBoard.populateThirdRow(lines[0].split(" "));
        bingoBoard.populateFourthRow(lines[0].split(" "));
        bingoBoard.populateFifthRow(lines[0].split(" "));
        return bingoBoard;
    }
}
