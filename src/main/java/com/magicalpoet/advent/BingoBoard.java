package com.magicalpoet.advent;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BingoBoard {
    // TODO: this could be optimized by using a single map for each row and column
    private BingoNumber b1;
    private BingoNumber b2;
    private BingoNumber b3;
    private BingoNumber b4;
    private BingoNumber b5;
    private BingoNumber i1;
    private BingoNumber i2;
    private BingoNumber i3;
    private BingoNumber i4;
    private BingoNumber i5;
    private BingoNumber n1;
    private BingoNumber n2;
    private BingoNumber n3;
    private BingoNumber n4;
    private BingoNumber n5;
    private BingoNumber g1;
    private BingoNumber g2;
    private BingoNumber g3;
    private BingoNumber g4;
    private BingoNumber g5;
    private BingoNumber o1;
    private BingoNumber o2;
    private BingoNumber o3;
    private BingoNumber o4;
    private BingoNumber o5;
    private List<BingoNumber> myNumbers = new ArrayList<>();
    private boolean isWinner = false;

    public boolean markNumberAndAnnounceIfBingo(Integer number) {
        if (myNumbers.isEmpty()) {
            myNumbers = List.of(b1, b2, b3, b4, b5, i1, i2, i3, i4, i5, n1, n2, n3, n4, n5, g1, g2, g3, g4, g5, o1, o2, o3, o4, o5);
        }
        myNumbers.forEach(myNumber -> {
            if (myNumber.getValue().equals(number)) {
                myNumber.setMarked(true);
            }
        });
        checkForBingo();
        return isWinner;
    }

    private void checkForBingo() {
        isWinner = (bingoOnRow() || bingoOnColumn());
    }

    private boolean bingoOnRow() {
        return allMarked(b1, i1, n1, g1, o1) ||
                allMarked(b2, i2, n2, g2, o2) ||
                allMarked(b3, i3, n3, g3, o3) ||
                allMarked(b4, i4, n4, g4, o4) ||
                allMarked(b5, i5, n5, g5, o5);
    }

    private boolean bingoOnColumn() {
        return allMarked(b1, b2, b3, b4, b5) ||
                allMarked(i1, i2, i3, i4, i5) ||
                allMarked(n1, n2, n3, n4, n5) ||
                allMarked(g1, g2, g3, g4, g5) ||
                allMarked(o1, o2, o3, o4, o5);
    }

    private boolean allMarked(BingoNumber first, BingoNumber second, BingoNumber third, BingoNumber fourth, BingoNumber fifth) {
        return first.getMarked() && second.getMarked() && third.getMarked() && fourth.getMarked() && fifth.getMarked();
    }

    public void populateFirstRow(String[] numbers) {
        b1 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[0])).build();
        i1 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[1])).build();
        n1 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[2])).build();
        g1 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[3])).build();
        o1 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[4])).build();
    }

    public void populateSecondRow(String[] numbers) {
        b2 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[0])).build();
        i2 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[1])).build();
        n2 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[2])).build();
        g2 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[3])).build();
        o2 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[4])).build();
    }

    public void populateThirdRow(String[] numbers) {
        b3 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[0])).build();
        i3 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[1])).build();
        n3 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[2])).build();
        g3 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[3])).build();
        o3 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[4])).build();
    }

    public void populateFourthRow(String[] numbers) {
        b4 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[0])).build();
        i4 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[1])).build();
        n4 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[2])).build();
        g4 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[3])).build();
        o4 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[4])).build();
    }

    public void populateFifthRow(String[] numbers) {
        b5 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[0])).build();
        i5 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[1])).build();
        n5 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[2])).build();
        g5 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[3])).build();
        o5 = BingoNumber.builder().marked(false).value(Integer.parseInt(numbers[4])).build();
    }

    @Builder
    @Getter
    @Setter
    public static class BingoNumber {
        private Boolean marked;
        private Integer value;
    }

}
