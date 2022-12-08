package com.magicalpoet.advent.twentytwo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06TuningTrouble {
    public int findStartOfPacket(String input) {
        return findPositionOfFirstUnique(input, 4);
    }
    public int findStartOfMessage(String input) {
        return findPositionOfFirstUnique(input, 14);
    }

    private int findPositionOfFirstUnique(String input, int desiredCount) {
        List<Character> buffer = new ArrayList<>();
        int i = 0;
        while (i < input.length()) {
            if (buffer.size() == desiredCount) {
                if (areUnique(buffer)) {
                    return i;
                }
                buffer.remove(0);
            }
            buffer.add(input.charAt(i));
            i++;
        }
        return -1;
    }

    private boolean areUnique(List<Character> buffer) {
        Set<Character> set = new HashSet<>(buffer);
        return set.size() == buffer.size();
    }
}
