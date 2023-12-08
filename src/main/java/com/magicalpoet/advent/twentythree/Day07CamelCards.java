package com.magicalpoet.advent.twentythree;

import com.magicalpoet.advent.Utils;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day07CamelCards {
    private static final Map<String, Integer> cardMapStandard = Map.of(
            "A", 14,
            "K", 13,
            "Q", 12,
            "J", 11,
            "T", 10
    );
    private static final Map<String, Integer> cardMapJokers = Map.of(
            "A", 14,
            "K", 13,
            "Q", 12,
            "J", 0,
            "T", 10
    );

    public int totalWinningsStandard(List<String> input) {
        List<Hand> hands = input.stream().map(this::createHandStandard).collect(Collectors.toList());
        return getTotalWinnings(hands);
    }

    public int totalWinningsWithJokers(List<String> input) {
        List<Hand> hands = input.stream().map(this::createHandWithJokers).collect(Collectors.toList());
        return getTotalWinnings(hands);
    }

    private int getTotalWinnings(List<Hand> hands) {
        sortHands(hands);
        int multiplier = hands.size();
        int totalWinnings = 0;
        for (Hand hand : hands) {
            totalWinnings += hand.wager * multiplier;
            multiplier--;
        }
        return totalWinnings;
    }

    private Hand createHandStandard(String data) {
        Hand hand = new Hand(data);
        hand.setRank(classifyHandStandard(hand.getCards()));
        return hand;
    }

    private Hand createHandWithJokers(String data) {
        Hand hand = new Hand(data);
        hand.setRank(classifyHandWithJokers(hand.getCards()));
        return hand;
    }

    public Rank classifyHandWithJokers(String hand) {
        Rank rank = classifyHandStandard(hand);
        int jokers = hand.length() - hand.replace("J", "").length();
        switch (jokers) {
            case 4 -> rank = Rank.FIVE_OF_A_KIND;
            case 3 -> {
                if (rank == Rank.THREE_OF_A_KIND) {
                    rank = Rank.FOUR_OF_A_KIND;
                } else {
                    rank = Rank.FIVE_OF_A_KIND;
                }
            }
            case 2 -> {
                if (rank == Rank.ONE_PAIR) {
                    rank = Rank.THREE_OF_A_KIND;
                } else if (rank == Rank.TWO_PAIR) {
                    rank = Rank.FOUR_OF_A_KIND;
                } else {
                    rank = Rank.FIVE_OF_A_KIND;
                }
            }
            case 1 -> {
                if (rank == Rank.HIGH_CARD) {
                    rank = Rank.ONE_PAIR;
                } else if (rank == Rank.ONE_PAIR) {
                    rank = Rank.THREE_OF_A_KIND;
                } else if (rank == Rank.TWO_PAIR) {
                    rank = Rank.FULL_HOUSE;
                } else if (rank == Rank.THREE_OF_A_KIND) {
                    rank = Rank.FOUR_OF_A_KIND;
                } else {
                    rank = Rank.FIVE_OF_A_KIND;
                }
            }
        }
        return rank;
    }

    public Rank classifyHandStandard(String hand) {
        final String[] cards = hand.split("");
        Map<String, Integer> cardsCount = new HashMap<>();
        Arrays.stream(cards).forEach(card -> Utils.incrementMapAtKey(cardsCount, card));

        if (cardsCount.size() == 1) {
            return Rank.FIVE_OF_A_KIND;
        }

        if (cardsCount.size() == 5) {
            return Rank.HIGH_CARD;
        }

        if (cardsCount.size() == 4) {
            return Rank.ONE_PAIR;
        }

        if (cardsCount.size() == 2) {
            if (cardsCount.containsValue(4)) {
                return Rank.FOUR_OF_A_KIND;
            }
            return Rank.FULL_HOUSE;
        }

        if (cardsCount.size() == 3) {
            if (cardsCount.containsValue(3)) {
                return Rank.THREE_OF_A_KIND;
            }
            return Rank.TWO_PAIR;
        }

        return null;
    }

    public void sortHands(List<Hand> hands) {
        hands.sort(Collections.reverseOrder());
    }

    @Getter
    @Setter
    public static class Hand implements Comparable<Hand> {
        private Rank rank;
        private int wager;
        private String cards;

        Hand(String input) {
            final String[] splitInput = input.split(" ");
            this.cards = splitInput[0];
            this.wager = Integer.parseInt(splitInput[1]);
        }

        public boolean equals(Object o) {
            if (!(o instanceof Hand hand)) {
                return false;
            }
            return hand.rank.value == rank.value;
        }

        public int compareTo(Hand hand) {
            if (rank != hand.rank) {
                return Integer.compare(rank.value, hand.rank.value);
            } else {
                return breakTie(this.cards.split(""), hand.cards.split(""));
            }
        }

        private int breakTie(String[] hand1, String[] hand2) {
            for (int i = 0; i < 5; i++) {
                if (!hand1[i].equals(hand2[i])) {
                    return Integer.compare(getIntValue(hand1[i]), getIntValue(hand2[i]));
                }
            }
            return 0;
        }

        private int getIntValue(String card) {
            try {
                return Integer.parseInt(card);
            } catch (Exception e) {
//                return cardMapStandard.get(card);
                return cardMapJokers.get(card);
            }
        }
    }

    public enum Rank {
        FIVE_OF_A_KIND(7),
        FOUR_OF_A_KIND(6),
        FULL_HOUSE(5),
        THREE_OF_A_KIND(4),
        TWO_PAIR(3),
        ONE_PAIR(2),
        HIGH_CARD(1);

        private final int value;

        Rank(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }
}
