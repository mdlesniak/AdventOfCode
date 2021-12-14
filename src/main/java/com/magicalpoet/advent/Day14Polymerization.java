package com.magicalpoet.advent;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14Polymerization {
    public long process(String base, String ruleBook, int iterations) {
        final String[] rules = ruleBook.split("\n");
        Map<String, String> rulesMap = Arrays.stream(rules)
                .map(rule -> rule.split(" -> "))
                .collect(Collectors.toMap(instructions -> instructions[0], instructions -> instructions[1], (a, b) -> b));
        String polymer = base;
        for (int i = 1; i <= iterations; i++) {
            polymer = polymerize(polymer, rulesMap);
        }

        final Map<String, Integer> elementsCount = countElements(polymer);
        final String mostPrevalentElement = Collections.max(elementsCount.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        final String leastPrevalentElement = Collections.min(elementsCount.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        return elementsCount.get(mostPrevalentElement).longValue() - elementsCount.get(leastPrevalentElement).longValue();
    }

    public String polymerize(String polymer, Map<String, String> rules) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < polymer.length() - 1; i++) {
            final char currentChar = polymer.charAt(i);
            result.append(currentChar);
            final char nextChar = polymer.charAt(i + 1);
            final String potentialPolymerization = "" + currentChar + nextChar;
            if (rules.containsKey(potentialPolymerization)) {
                result.append(rules.get(potentialPolymerization));
            }
        }
        result.append(polymer.charAt(polymer.length() - 1));
        return result.toString();
    }

    public Map<String, Integer> countElements(String polymer) {
        Map<String, Integer> elementsCount = new HashMap<>();
        final String[] elements = polymer.split("");
        Arrays.stream(elements).forEach(element -> {
            elementsCount.putIfAbsent(element, 0);
            elementsCount.put(element, elementsCount.get(element) + 1);
        });
        return elementsCount;
    }
}
