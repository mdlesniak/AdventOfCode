package com.magicalpoet.advent.twentythree;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.magicalpoet.advent.twentythree.Day07CamelCards.Rank.FIVE_OF_A_KIND;
import static com.magicalpoet.advent.twentythree.Day07CamelCards.Rank.FOUR_OF_A_KIND;
import static com.magicalpoet.advent.twentythree.Day07CamelCards.Rank.FULL_HOUSE;
import static com.magicalpoet.advent.twentythree.Day07CamelCards.Rank.HIGH_CARD;
import static com.magicalpoet.advent.twentythree.Day07CamelCards.Rank.ONE_PAIR;
import static com.magicalpoet.advent.twentythree.Day07CamelCards.Rank.THREE_OF_A_KIND;
import static com.magicalpoet.advent.twentythree.Day07CamelCards.Rank.TWO_PAIR;
import static org.assertj.core.api.Assertions.assertThat;

class Day07CamelCardsTest {
    private Day07CamelCards camelCards;
    private List<String> data;
    private TestUtils utils = new TestUtils();


    @BeforeEach
    void setup() throws Exception {
        camelCards = new Day07CamelCards();
        data = utils.readFileToList("2023/day07Input.txt");
    }

    @Test
    void classifiesHands() {
        assertThat(camelCards.classifyHand("32T9K")).isEqualTo(HIGH_CARD);
        assertThat(camelCards.classifyHand("32T3K")).isEqualTo(ONE_PAIR);
        assertThat(camelCards.classifyHand("KK677")).isEqualTo(TWO_PAIR);
        assertThat(camelCards.classifyHand("A8A88")).isEqualTo(FULL_HOUSE);
        assertThat(camelCards.classifyHand("J7777")).isEqualTo(FOUR_OF_A_KIND);
        assertThat(camelCards.classifyHand("AAAAA")).isEqualTo(FIVE_OF_A_KIND);
        assertThat(camelCards.classifyHand("T55J5")).isEqualTo(THREE_OF_A_KIND);
    }

    @Test
    void createsHand() {
        final String input = "J7777 123";

        Day07CamelCards.Hand hand = camelCards.createHand(input);

        assertThat(hand.getCards()).isEqualTo("J7777");
        assertThat(hand.getWager()).isEqualTo(123);
        assertThat(hand.getRank()).isEqualTo(FOUR_OF_A_KIND);
    }

    @Test
    void sortsHands() {
        final List<String> input = List.of(
                "32T3K 765",
                "T55J5 684",
                "KK677 28",
                "KTJJT 220",
                "QQQJA 483");
        final List<Day07CamelCards.Hand> hands = input.stream().map(camelCards::createHand).collect(Collectors.toList());

        camelCards.sortHands(hands);

        assertThat(hands.get(0).getCards()).isEqualTo("QQQJA");
        assertThat(hands.get(1).getCards()).isEqualTo("T55J5");
        assertThat(hands.get(2).getCards()).isEqualTo("KK677");
        assertThat(hands.get(3).getCards()).isEqualTo("KTJJT");
        assertThat(hands.get(4).getCards()).isEqualTo("32T3K");
    }

    @Test
    void partOneTest() {
        final List<String> input = List.of(
                "32T3K 765",
                "T55J5 684",
                "KK677 28",
                "KTJJT 220",
                "QQQJA 483");
        assertThat(camelCards.totalWinnings(input)).isEqualTo(6440);
    }

    @Test
    void partOne() {
        assertThat(camelCards.totalWinnings(data)).isEqualTo(251806792);
    }
}
