package com.magicalpoet.advent.twentytwo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Day10CathodeRay {
    private Integer register = 1;
    private Integer cycleCount = 1;
    private List<Integer> desiredCycles = List.of(20, 60, 100, 140, 180, 220);

    public Integer totalSignalStrenths(List<Integer> signalStrengths) {
        return signalStrengths.stream().mapToInt(Integer::intValue).sum();
    }
    public List<Integer> calculateSignalStrengths(String input) {
        List<Integer> strengthScores = new ArrayList<>();
        List<String> instructions = Arrays.stream(input.split("\n")).toList();
        String processing = null;

        for (String instruction : instructions) {
            if (desiredCycles.contains(cycleCount)) {
                strengthScores.add(cycleCount * register);
            }
            if (processing != null) {
                processInstruction(processing);
                cycleCount++;
                if (desiredCycles.contains(cycleCount)) {
                    strengthScores.add(cycleCount * register);
                }
            }
            if (instruction.startsWith("add")) {
                processing = instruction;
            } else {
                processing = null;
            }
            cycleCount++;
        }

        return strengthScores;
    }

    private void processInstruction(String instruction) {
        String s = instruction.split(" ")[1];
        register += Integer.parseInt(s);
    }
}
