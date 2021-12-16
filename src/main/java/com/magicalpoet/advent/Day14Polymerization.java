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

        Map<Integer, String> polymerMap = new HashMap<>();
        final String[] elements = base.split("");
        for (int i = 1, elementsLength = elements.length; i <= elementsLength; i++) {
            polymerMap.put(i, elements[i - 1]);
        }

        for (int i = 1; i <= iterations; i++) {
            polymerMap = polymerize(polymerMap, rulesMap);
        }

        final Map<String, Integer> elementsCount = countElements(polymerMap);
        final String mostPrevalentElement = Collections.max(elementsCount.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        final String leastPrevalentElement = Collections.min(elementsCount.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        return elementsCount.get(mostPrevalentElement).longValue() - elementsCount.get(leastPrevalentElement).longValue();
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

        for (int i = 35; i <= iterations; i++) {
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


    public Map<Integer, String> polymerize(Map<Integer, String> polymer, Map<String, String> rules) {
        Map<Integer, String> result = new HashMap<>();
        for (int i = 1; i < polymer.size(); i++) {
            final String currentElement = polymer.get(i);
            result.put(result.size() + 1, currentElement);
            final String nextElement = polymer.get(i + 1);
            final String potentialPolymerization = currentElement + nextElement;
            if (rules.containsKey(potentialPolymerization)) {
                result.put(result.size() + 1, rules.get(potentialPolymerization));
            }
        }
        result.put(result.size() + 1, polymer.get(polymer.size())); // put last character
        return result;
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

    public Map<String, Integer> countElements(Map<Integer, String> polymer) {
        Map<String, Integer> elementsCount = new HashMap<>();
        polymer.values().forEach(element -> {
            elementsCount.putIfAbsent(element, 0);
            elementsCount.put(element, elementsCount.get(element) + 1);
        });
        return elementsCount;
    }
}
