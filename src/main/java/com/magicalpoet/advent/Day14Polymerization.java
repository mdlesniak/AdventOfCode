package com.magicalpoet.advent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14Polymerization {
    private static final File DIR = new File(".");
    private final String filePath;

    Day14Polymerization() throws IOException {
        this.filePath = DIR.getCanonicalPath() + File.separator;
    }

    public long process(String base, String ruleBook, int iterations) {
        final String[] rules = ruleBook.split("\n");
        Map<String, String> rulesMap = Arrays.stream(rules)
                .map(rule -> rule.split(" -> "))
                .collect(Collectors.toMap(instructions -> instructions[0], instructions -> instructions[1], (a, b) -> b));

        Map<String, Long> polymerMap = new HashMap<>();
        final String[] elements = base.split("");
        for (int i = 1, elementsLength = elements.length - 1; i <= elementsLength; i++) {
            polymerMap.put(elements[i - 1] + elements[i], 1L);
        }

        for (int i = 1; i <= iterations; i++) {
            polymerMap = polymerize(polymerMap, rulesMap);
        }

        final Map<String, Long> elementsCount = countElements(polymerMap);
        final String mostPrevalentElement = Collections.max(elementsCount.entrySet(), Comparator.comparingLong(Map.Entry::getValue)).getKey();
        final String leastPrevalentElement = Collections.min(elementsCount.entrySet(), Comparator.comparingLong(Map.Entry::getValue)).getKey();
        long initialCount = (elementsCount.get(mostPrevalentElement) - elementsCount.get(leastPrevalentElement)) / 2;
        if (mostPrevalentElement.equals(elements[0])) {
            initialCount++;
        }
        if (mostPrevalentElement.equals(elements[elements.length - 1])) {
            initialCount++;
        }
        return initialCount;
    }

    public Map<String, Long> polymerize(Map<String, Long> polymerMap, Map<String, String> rules) {
        Map<String, Long> result = new HashMap<>();
        for (Map.Entry<String, Long> entry : polymerMap.entrySet()) {
            final String currentPair = entry.getKey();
            if (rules.containsKey(currentPair)) {
                // grow and add new pairs
                final String[] currentPairSplit = currentPair.split("");
                final String elementToAdd = rules.get(currentPair);
                List<String> newPairs = List.of(currentPairSplit[0] + elementToAdd, elementToAdd + currentPairSplit[1]);
                newPairs.forEach(newPair -> {
                    if (result.containsKey(newPair)) {
                        result.put(newPair, entry.getValue() + result.get(newPair));
                    } else {
                        result.put(newPair, entry.getValue());
                    }
                });
            } else {
                // add the original pairs without growth
                if (result.containsKey(currentPair)) {
                    result.put(currentPair, entry.getValue() + result.get(currentPair));
                } else {
                    result.put(currentPair, entry.getValue());
                }
            }
        }
        return result;
    }

    public Map<String, Long> countElements(Map<String, Long> polymer) {
        Map<String, Long> elementsCount = new HashMap<>();
        polymer.forEach((key, value) -> Arrays.stream(key.split("")).forEach(element -> {
            if (elementsCount.containsKey(element)) {
                elementsCount.put(element, elementsCount.get(element) + value);
            } else {
                elementsCount.put(element, value);
            }
        }));
        return elementsCount;
    }

    public long processUsingFiles(String base, String ruleBook, int iterations) throws Exception {
        final String[] rules = ruleBook.split("\n");
        Map<String, String> rulesMap = Arrays.stream(rules)
                .map(rule -> rule.split(" -> "))
                .collect(Collectors.toMap(instructions -> instructions[0], instructions -> instructions[1], (a, b) -> b));

        File baseFile = new File(filePath + "polymer34");
        FileWriter fstream = new FileWriter(baseFile, true);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write(base);
        out.close();

        for (int i = 1; i <= iterations; i++) {
            polymerizeUsingFiles(rulesMap, i);
        }

        File finalPolymer = new File(filePath + "polymer" + iterations);
        final Map<String, Integer> elementsCount = countElements(finalPolymer);
        final String mostPrevalentElement = Collections.max(elementsCount.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        final String leastPrevalentElement = Collections.min(elementsCount.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        return elementsCount.get(mostPrevalentElement).longValue() - elementsCount.get(leastPrevalentElement).longValue();
    }

    private Map<String, Integer> countElements(File finalPolymer) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(finalPolymer)));
        Map<String, Integer> elementsCount = new HashMap<>();
        int character;
        while ((character = in.read()) != -1) {
            String element = "" + character;
            elementsCount.putIfAbsent(element, 0);
            elementsCount.put(element, elementsCount.get(element) + 1);
        }
        in.close();
        return elementsCount;
    }

    private void polymerizeUsingFiles(Map<String, String> rulesMap, int iteration) throws Exception {
        File inFile = new File(filePath + "polymer" + (iteration - 1));
        File outFile = new File(filePath + "polymer" + iteration);

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
        BufferedWriter out = new BufferedWriter(new FileWriter(outFile, true));

        int c;
        char previousChar = 0;
        while ((c = in.read()) != -1) {
            char currentChar = (char) c;
            if (previousChar != 0) {
                out.write(previousChar);
                String potentialPolymerization = "" + previousChar + currentChar;
                if (rulesMap.containsKey(potentialPolymerization)) {
                    out.write(rulesMap.get(potentialPolymerization));
                }
            }
            previousChar = currentChar;
        }
        out.write(previousChar);

        in.close();
        out.close();
    }

}
