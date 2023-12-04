package com.magicalpoet.advent.twentythree;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04ScratchCards {
    private final Pattern cardName = Pattern.compile("Card \\d+:\\s+");

    public int copyCards(String input) {
        final String[] cards = input.split("\\n");
        Map<Integer, Integer> cardsCount = new HashMap<>();
        for (int i = 0; i < cards.length; i++) {
            cardsCount.put(i, 1);
        }
        for (int i = 0; i < cards.length; i++) {
            int winningNumbers = findWinners(cards[i]).size();
            while (winningNumbers > 0) {
                int currentCount = cardsCount.get(i + winningNumbers);
                int copies = cardsCount.get(i);
                cardsCount.put(i + winningNumbers, copies + currentCount);
                winningNumbers--;
            }
        }
        return cardsCount.values().stream().reduce(0, Integer::sum);
    }

    public int scoreCards(String input) {
        final String[] cards = input.split("\\n");
        AtomicInteger totalScore = new AtomicInteger();
        Arrays.stream(cards).forEach(card -> totalScore.addAndGet(scoreCard(card)));
        return totalScore.get();
    }

    public int scoreCard(String card) {
        final List<String> winners = findWinners(card);
        if (winners.isEmpty()) {
            return 0;
        }
        return (int) Math.pow(2, (double) winners.size() - 1);
    }

    public List<String> findWinners(String card) {
        Matcher matcher = cardName.matcher(card);
        final String[] numberGroups = matcher.replaceAll("").split("\\s+\\|\\s+");
        List<String> winners = new ArrayList<>(Arrays.asList(numberGroups[0].split("\\s+")));
        List<String> myNumbers = new ArrayList<>(Arrays.asList(numberGroups[1].split("\\s+")));
        myNumbers.retainAll(winners);
        return myNumbers;
    }
}
