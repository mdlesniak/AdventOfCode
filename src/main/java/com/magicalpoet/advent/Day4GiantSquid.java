package com.magicalpoet.advent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4GiantSquid {
    public int findFirstWinningScore(String numbersData, String boardsData) throws Exception {
        List<BingoBoard> boards = getBoards(boardsData);
        List<Integer> numbers = Arrays.stream(numbersData.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        int winningScore = 0;
        for (Integer number : numbers) {
            for (BingoBoard board : boards) {
                boolean isWinner = board.markNumberAndAnnounceIfBingo(number);
                if (isWinner) {
                    winningScore = number * totalUnmarkedNumbers(board);
                    break;
                }
            }
            if (winningScore > 0) {
                break;
            }
        }
        return winningScore;
    }

    public int findLastWinningScore(String numbersData, String boardsData) throws Exception {
        List<BingoBoard> boards = getBoards(boardsData);
        List<Integer> numbers = Arrays.stream(numbersData.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        int score = 0;
        for (Integer number : numbers) {
            List<BingoBoard> nonWinners = boards.stream().filter(board -> !board.isWinner()).collect(Collectors.toList());
            for (BingoBoard board : nonWinners) {
                boolean isWinner = board.markNumberAndAnnounceIfBingo(number);
                if (isWinner) {
                    boards.remove(board);
                    score = number * totalUnmarkedNumbers(board);
                }
            }
            if (boards.isEmpty()) {
                break;
            }
        }
        return score;
    }

    public int totalUnmarkedNumbers(BingoBoard bingoBoard) {
        int total = 0;
        List<BingoBoard.BingoNumber> unmarkedNumbers =
                bingoBoard.getMyNumbers().stream().filter(board -> !board.getMarked()).collect(Collectors.toList());
        for (BingoBoard.BingoNumber bingoNumber : unmarkedNumbers) {
            total = total + bingoNumber.getValue();
        }
        return total;
    }

    public List<BingoBoard> getBoards(String data) throws Exception {
        String[] boards = data.split("\n\n");
        return Arrays.stream(boards).map(this::createBoard).collect(Collectors.toList());
    }

    public BingoBoard createBoard(String board) {
        BingoBoard bingoBoard = new BingoBoard();
        String[] lines = board.split("\n");
        bingoBoard.populateFirstRow(lines[0].split(" "));
        bingoBoard.populateSecondRow(lines[1].split(" "));
        bingoBoard.populateThirdRow(lines[2].split(" "));
        bingoBoard.populateFourthRow(lines[3].split(" "));
        bingoBoard.populateFifthRow(lines[4].split(" "));
        return bingoBoard;
    }
}
