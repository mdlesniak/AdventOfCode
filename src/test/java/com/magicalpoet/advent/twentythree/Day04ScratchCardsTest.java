package com.magicalpoet.advent.twentythree;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day04ScratchCardsTest {
    private final TestUtils utils = new TestUtils();
    private String data;
    private Day04ScratchCards scratchCards;


    @BeforeEach
    void setup() throws Exception {
        data = utils.readFileToString("2023/day04Input.txt");
        scratchCards = new Day04ScratchCards();
    }

    @Test
    void findsWinners() {
        String card1 = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
        String card5 = "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36";
        assertThat(scratchCards.findWinners(card1)).containsExactlyInAnyOrder(
                "48", "83", "17", "86"
        );
        assertThat(scratchCards.findWinners(card5)).isEmpty();
    }

    @Test
    void scoresCard() {
        String card1 = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
        String card5 = "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36";
        assertThat(scratchCards.scoreCard(card1)).isEqualTo(8);
        assertThat(scratchCards.scoreCard(card5)).isZero();
    }

    @Test
    void scoresCards() {
        String cards = """
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11""";
        assertThat(scratchCards.scoreCards(cards)).isEqualTo(13);
    }

    @Test
    void partOne() {
        assertThat(scratchCards.scoreCards(data)).isEqualTo(26218);
    }

    @Test
    void copiesCards() {
        String cards = """
                Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
                Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
                Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
                Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
                Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
                Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11""";
        assertThat(scratchCards.copyCards(cards)).isEqualTo(30);
    }

    @Test
    void partTwo() {
        assertThat(scratchCards.copyCards(data)).isEqualTo(9997537);
    }
}
