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
    void classifiesHandsStandard() {
        assertThat(camelCards.classifyHandStandard("32T9K")).isEqualTo(HIGH_CARD);
        assertThat(camelCards.classifyHandStandard("32T3K")).isEqualTo(ONE_PAIR);
        assertThat(camelCards.classifyHandStandard("KK677")).isEqualTo(TWO_PAIR);
        assertThat(camelCards.classifyHandStandard("A8A88")).isEqualTo(FULL_HOUSE);
        assertThat(camelCards.classifyHandStandard("J7777")).isEqualTo(FOUR_OF_A_KIND);
        assertThat(camelCards.classifyHandStandard("AAAAA")).isEqualTo(FIVE_OF_A_KIND);
        assertThat(camelCards.classifyHandStandard("T55J5")).isEqualTo(THREE_OF_A_KIND);
    }

    @Test
    void sortsHandsStandard() {
        final List<String> input = List.of(
                "32T3K 765",
                "T55J5 684",
                "KK677 28",
                "KTJJT 220",
                "QQQJA 483");
        final List<Day07CamelCards.Hand> hands = input.stream().map(input1 -> {
            Day07CamelCards.Hand hand = new Day07CamelCards.Hand(input1);
            hand.setRank(camelCards.classifyHandStandard(hand.getCards()));
            return hand;
        }).collect(Collectors.toList());

        camelCards.sortHands(hands);

        assertThat(hands.get(0).getCards()).isEqualTo("QQQJA");
        assertThat(hands.get(1).getCards()).isEqualTo("T55J5");
        assertThat(hands.get(2).getCards()).isEqualTo("KK677");
        assertThat(hands.get(3).getCards()).isEqualTo("KTJJT");
        assertThat(hands.get(4).getCards()).isEqualTo("32T3K");
    }

    @Test
    void partOneTest() {
        List<String> input = List.of(
                "32T3K 765",
                "T55J5 684",
                "KK677 28",
                "KTJJT 220",
                "QQQJA 483");
        assertThat(camelCards.totalWinningsStandard(input)).isEqualTo(6440);
    }

    @Test
    void partOne() {
        assertThat(camelCards.totalWinningsStandard(data)).isEqualTo(251806792);
    }

    @Test
    void classifiesHandsWithJokers() {
        assertThat(camelCards.classifyHandWithJokers("32T9K")).isEqualTo(HIGH_CARD);
        assertThat(camelCards.classifyHandWithJokers("3JT9K")).isEqualTo(ONE_PAIR);
        assertThat(camelCards.classifyHandWithJokers("3JT99")).isEqualTo(THREE_OF_A_KIND);
        assertThat(camelCards.classifyHandWithJokers("KK677")).isEqualTo(TWO_PAIR);
        assertThat(camelCards.classifyHandWithJokers("KKJ77")).isEqualTo(FULL_HOUSE);
        assertThat(camelCards.classifyHandWithJokers("T55J5")).isEqualTo(FOUR_OF_A_KIND);
        assertThat(camelCards.classifyHandWithJokers("KTJJT")).isEqualTo(FOUR_OF_A_KIND);
        assertThat(camelCards.classifyHandWithJokers("QQQJA")).isEqualTo(FOUR_OF_A_KIND);
        assertThat(camelCards.classifyHandWithJokers("JJ249")).isEqualTo(THREE_OF_A_KIND);
        assertThat(camelCards.classifyHandWithJokers("JJJ99")).isEqualTo(FIVE_OF_A_KIND);
        assertThat(camelCards.classifyHandWithJokers("JJ299")).isEqualTo(FOUR_OF_A_KIND);
        assertThat(camelCards.classifyHandWithJokers("J7777")).isEqualTo(FIVE_OF_A_KIND);
        assertThat(camelCards.classifyHandWithJokers("JJJ37")).isEqualTo(FOUR_OF_A_KIND);
        assertThat(camelCards.classifyHandWithJokers("JJJ77")).isEqualTo(FIVE_OF_A_KIND);
        assertThat(camelCards.classifyHandWithJokers("JJJJJ")).isEqualTo(FIVE_OF_A_KIND);
    }

    @Test
    void sortsHandsWithJokers() {
        final List<String> input = List.of(
                "32T3K 765",
                "T55J5 684",
                "KK677 28",
                "KTJJT 220",
                "QQQJA 483");
        final List<Day07CamelCards.Hand> hands = input.stream().map(input1 -> {
            Day07CamelCards.Hand hand = new Day07CamelCards.Hand(input1);
            hand.setRank(camelCards.classifyHandWithJokers(hand.getCards()));
            return hand;
        }).collect(Collectors.toList());

        camelCards.sortHands(hands);

        assertThat(hands.get(0).getCards()).isEqualTo("KTJJT");
        assertThat(hands.get(1).getCards()).isEqualTo("QQQJA");
        assertThat(hands.get(2).getCards()).isEqualTo("T55J5");
        assertThat(hands.get(3).getCards()).isEqualTo("KK677");
        assertThat(hands.get(4).getCards()).isEqualTo("32T3K");
    }


    @Test
    void partTwoTest() {
        List<String> input = List.of(
                "32T3K 765",
                "T55J5 684",
                "KK677 28",
                "KTJJT 220",
                "QQQJA 483");
        assertThat(camelCards.totalWinningsWithJokers(input)).isEqualTo(5905);
    }

    @Test
    void partTwo() {
        assertThat(camelCards.totalWinningsWithJokers(data)).isEqualTo(252113488);
    }


}
