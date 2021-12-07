package com.magicalpoet.advent;

import java.util.List;

// TODO: combine the two Day2 classes
public class Day2Dive2 {
    private int depth = 0;
    private int horizontal = 0;
    private int aim = 0;

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
            aimUp(magnitude);
        } else if ("down".equals(commandWord)) {
            aimDown(magnitude);
        } else {
            throw new RuntimeException("Unrecognized command word: " + commandWord);
        }
    }

    private void aimDown(int magnitude) {
        aim = aim + magnitude;
    }

    private void aimUp(int magnitude) {
        aim = aim - magnitude;
    }

    private void goForward(int magnitude) {
        this.horizontal = this.horizontal + magnitude;
        this.depth = this.depth + magnitude * this.aim;
    }
}
