package com.magicalpoet.advent.twentyone;

import com.magicalpoet.advent.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

public class Day10SyntaxScoring {
    private static final Map<String, String> characterPairs = Map.of(
            "(", ")",
            "[", "]",
            "{", "}",
            "<", ">"
    );
    private static final Map<String, Integer> corruptionPointsMap = Map.of(
            ")", 3,
            "]", 57,
            "}", 1197,
            ">", 25137
    );
    private static final Map<String, Integer> autocompletePointsMap = Map.of(
            ")", 1,
            "]", 2,
            "}", 3,
            ">", 4
    );

    public long calculateAutocompleteScore(String input) {
        String[] lines = input.split("\n");
        List<Long> scores = Arrays.stream(lines)
                .mapToLong(line -> scoreAutocompletion(findCompletion(line)))
                .filter(score -> score > 0)
                .boxed().collect(Collectors.toList());
        return Utils.findMedian(scores.stream().mapToLong(Long::longValue).toArray());
    }

    public int calculateCorruptionScore(String input) {
        String[] lines = input.split("\n");
        int score = 0;
        for (String line : lines) {
            try {
                String corruption = findCorruption(line);
                if (!corruption.isBlank()) {
                    score = score + corruptionPointsMap.get(corruption);
                }
            } catch (IncompleteLineException e) {
                // do nothing
            }
        }
        return score;
    }

    // TODO: make this an optional
    public String findCompletion(String line) {
        StringBuilder closingCharacters = new StringBuilder();
        try {
            findCorruption(line);
        } catch (IncompleteLineException e) {
            ListIterator<String> iterator = e.getUnclosedCharacters().listIterator(e.getUnclosedCharacters().size());
            while (iterator.hasPrevious()) {
                String characterToClose = iterator.previous();
                closingCharacters.append(characterPairs.get(characterToClose));
            }
        }
        return closingCharacters.toString();
    }

    // TODO: make this return an optional
    public String findCorruption(String line) throws IncompleteLineException {
        String[] characters = line.split("");
        ArrayList<String> currentOpenCharacters = new ArrayList<>();
        for (String character : characters) {
            if (characterPairs.containsKey(character)) {
                currentOpenCharacters.add(character);
            } else { // ignoring unknown chars for now
                String badCharacter = handleCloseCharacter(character, currentOpenCharacters);
                if (!badCharacter.isBlank()) {
                    return badCharacter;
                }
            }
        }
        if (currentOpenCharacters.isEmpty()) {
            return "";
        } else {
            throw new IncompleteLineException(currentOpenCharacters);
        }
    }

    public long scoreAutocompletion(String completion) {
        if (completion.isBlank()) {
            return 0;
        }
        long total = 0;
        for (String character : completion.split("")) {
            total = total * 5 + autocompletePointsMap.get(character);
        }
        return total;
    }

    private String handleCloseCharacter(String character, ArrayList<String> currentOpenCharacters) {
        if (currentOpenCharacters.isEmpty()) {
            return character;
        }
        final int lastOpen = currentOpenCharacters.size() - 1;
        if (characterPairs.get(currentOpenCharacters.get(lastOpen)).equals(character)) {
            currentOpenCharacters.remove(lastOpen);
            return "";
        } else {
            return character;
        }
    }

    @AllArgsConstructor
    @Getter
    public static class IncompleteLineException extends Exception {
        private final ArrayList<String> unclosedCharacters;
    }
}
