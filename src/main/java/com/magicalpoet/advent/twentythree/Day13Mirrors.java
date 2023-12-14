package com.magicalpoet.advent.twentythree;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Getter
public class Day13Mirrors {
    private final List<Integer> columnScores = new ArrayList<>();
    private final List<Integer> rowScores = new ArrayList<>();

    public void summarize(String[] boardData) {
        final Stream<Board> boardStream = Arrays.stream(boardData).map(Board::new);
        boardStream.forEach(board -> {
            columnScores.add(board.getVerticalReflection());
            rowScores.add(board.getHorizontalReflection());
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
//            for (int i = 1; i < rows[0].length(); i++) {
//            }

            int axisOption = rows[0].length() / 2;
            if (rowsReflect(0, rows[0].length() - 1)) {
                return axisOption;
            }
            if (rowsReflect(0, rows[0].length() - 2)) {
                return axisOption;
            }
            if (rowsReflect(1, rows[0].length() - 1)) {
                return axisOption + 1;
            }
            return 0;
        }

        private boolean rowsReflect(int leftIndex, int rightIndex) {
            for (String row : rows) {
                if (!rowReflects(row.split(""), leftIndex, rightIndex)) {
                    return false;
                }
            }
            return true;
        }

        private static boolean rowReflects(String[] characters, int leftIndex, int rightIndex) {
            while (leftIndex <= characters.length / 2) {
                if (!characters[leftIndex].equals(characters[rightIndex])) {
                    return false;
                }
                leftIndex++;
                rightIndex--;
            }
            return true;
        }

        public int getHorizontalReflection() {
            for (int i = 1; i < rows.length; i++) {
                for (int above = i, below = i; above > 0; above--, below++) {
                    if (!rows[above - 1].equals(rows[below])) {
                        break;
                    }
                }
                return i;
            }

            //            if (columnsReflect(0, rows.length - 1)) {
//                return rows.length / 2;
//            }
//            if (columnsReflect(0, rows.length - 2)) {
//                return rows.length / 2;
//            }
//            if (columnsReflect(1, rows.length - 1)) {
//                return rows.length / 2 + 1;
//            }
            return 0;
        }

        private boolean columnsReflect(int topRow, int bottomRow) {
            while (topRow <= bottomRow) {
                for (int i = 0; i < rows[topRow].length() - 1; i++) {
                    if (rows[topRow].charAt(i) != rows[bottomRow].charAt(i)) {
                        return false;
                    }
                }
                topRow++;
                bottomRow--;
            }
            return true;
        }

        private static String[] setRows(String boardData) {
            return boardData.split("\\n");
        }
    }
}
