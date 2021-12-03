package com.magicalpoet.advent;

import java.util.List;

public class Day2Dive1 {
    private int depth = 0;
    private int horizontal = 0;

    public int findSubPosition(List<String> movementLog) {
        movementLog.forEach(this::processLine);
        return depth * horizontal;
    }

    private void processLine(String input) {
        final String[] command = input.split(" ");
        final String commandWord = command[0];
        final int magnitude = Integer.parseInt(command[1]);
        if ("forward".equals(commandWord)) {
            goForward(magnitude);
        } else if ("up".equals(commandWord)) {
            goUp(magnitude);
        } else if ("down".equals(commandWord)) {
            goDown(magnitude);
        } else {
            throw new RuntimeException("Unrecognized command word: " + commandWord);
        }
    }

    private void goDown(int magnitude) {
        depth = depth + magnitude;
    }

    private void goUp(int magnitude) {
        depth = depth - magnitude;
    }

    private void goForward(int magnitude) {
        this.horizontal = this.horizontal + magnitude;
    }
}
