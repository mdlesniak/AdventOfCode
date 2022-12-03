package com.magicalpoet.advent.twentytwo;

import java.util.Arrays;

public class Day02RockPaperScissors {
    public int scoreMatch(String input) {
        String[] rounds = input.split("\n");
        return Arrays.stream(rounds).mapToInt(this::scoreRound).sum();
    }

    public int scoreMatchSecondWay(String input) {
        String[] rounds = input.split("\n");
        return Arrays.stream(rounds).mapToInt(
                round -> scoreRound(translateRoundPlay(round))
        ).sum();
    }

    public int scoreRound(String round) {
        int score = 0;
        String[] plays = round.split(" ");
        String opponent = plays[0];
        String me = plays[1];
        score += scoreOutcome(opponent, me);
        score += scoreShapePoints(me);
        return score;
    }

    private String translateRoundPlay(String round) {
        String[] plays = round.split(" ");
        String opponent = plays[0];
        String desiredResult = plays[1];
        String me = "";
        switch (opponent) {
            case "A":
                if (desiredResult.equals("X")) {
                    me = "Z";
                } else if (desiredResult.equals("Y")) {
                    me = "X";
                } else {
                    me = "Y";
                }
                break;
            case "B":
                if (desiredResult.equals("X")) {
                    me = "X";
                } else if (desiredResult.equals("Y")) {
                    me = "Y";
                } else {
                    me = "Z";
                }
                break;
            default:
                if (desiredResult.equals("X")) {
                    me = "Y";
                } else if (desiredResult.equals("Y")) {
                    me = "Z";
                } else {
                    me = "X";
                }
                break;
        }
        return opponent + " " + me;
    }

    private int scoreShapePoints(String me) {
        switch (me) {
            case "X":
                return 1;
            case "Y":
                return 2;
            case "Z":
                return 3;
            default:
                throw new IllegalStateException("Unexpected value: " + me);
        }
    }

    private int scoreOutcome(String opponent, String me) {
        if (isDraw(opponent, me)) {
            return 3;
        } else if (isWin(opponent, me)) {
            return 6;
        } else {
            return 0;
        }
    }

    private boolean isWin(String opponent, String me) {
        // A,X = Rock
        // B,Y = Paper
        // C,Z = Scissors
        switch (opponent) {
            case "A":
                return me.equals("Y");
            case "B":
                return me.equals("Z");
            default:
                return me.equals("X");
        }
    }

    private boolean isDraw(String opponent, String me) {
        return (opponent.equals("A") && me.equals("X")) ||
                (opponent.equals("B") && me.equals("Y")) ||
                (opponent.equals("C") && me.equals("Z"));
    }
}
