package com.magicalpoet.advent.twentythree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day13Mirrors {
    public List<Integer> columns = new ArrayList<>();
    public List<Integer> rows = new ArrayList<>();

    public void summarize(String[] boardData) {
        final Stream<Board> boardStream = Arrays.stream(boardData).map(Board::new);
        boardStream.forEach(board -> {
            final int verticalReflection = board.getVerticalReflection();
            if (verticalReflection == 0) {
                rows.add(board.getHorizontalReflection());
            } else {
                columns.add(verticalReflection);
            }
        });
    }

    public int summarize(List<Integer> columns, List<Integer> rows) {
        final int columnsSum = columns.stream().mapToInt(Integer::intValue).sum();
        final int rowsSum = rows.stream().mapToInt(Integer::intValue).sum();
        return columnsSum + rowsSum * 100;
    }

    public static class Board {
        private final String[] rows;

        public Board(String boardData) {
            this.rows = setRows(boardData);
        }

        public int getVerticalReflection() {
            boolean reflects = true;
            // assume odd number of characters in a row for now
            // check first reflection option
            for (String row : rows) {
                final String[] characters = row.split("");
                final String[] newCharacters = Arrays.copyOfRange(characters, 0, characters.length - 1);
                reflects = doesRowReflect(newCharacters);
            }
            if (reflects) {
                return rows[0].length() / 2;
            }
            //check second reflection option
            for (String row : rows) {
                String[] characters = row.split("");
                final String[] newCharacters = Arrays.copyOfRange(characters, 1, characters.length);
                reflects = doesRowReflect(newCharacters);
            }
            if (reflects) {
                return rows[0].length() / 2 + 1;
            }
            return 0;
        }

        public int getHorizontalReflection() {
            // assume odd number of rows for now
            if (columnsReflect(0, rows.length - 2)) {
                return rows.length / 2;
            }
            if (columnsReflect(1, rows.length - 1)) {
                return rows.length / 2 + 1;
            }
            return 0;
        }

        private boolean columnsReflect(int startRow, int endRow) {
            for (int top = startRow, bottom = endRow; top <= bottom; top++, bottom--) {
                for (int i = 0; i < rows[top].length() - 1; i++) {
                    if (rows[top].charAt(i) != rows[bottom].charAt(i)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private static boolean doesRowReflect(String[] characters) {
            for (int leftIndex = 0, rightIndex = characters.length - 1; leftIndex <= characters.length / 2; leftIndex++, rightIndex--) {
                if (!characters[leftIndex].equals(characters[rightIndex])) {
                    return false;
                }
            }
            return true;
        }

        private static String[] setRows(String boardData) {
            return boardData.split("\\n");
        }
    }
}
