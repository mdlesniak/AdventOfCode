package com.magicalpoet.advent.twentytwo;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day03RucksackTest {
    private static final String TEST_INPUT = "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
            "PmmdzqPrVvPwwTWBwg\n" +
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
            "ttgJtRGJQctTZtZT\n" +
            "CrZsJsPPZsGzwwsLwLmpwMDw";
    private final TestUtils utils = new TestUtils();
    private Day03Rucksack rucksack;

    @BeforeEach
    void setUp() {
        rucksack = new Day03Rucksack();
    }

    @Test
    void canSplitSack() {
        String[] result = rucksack.splitSack(TEST_INPUT.split("\n")[0]);
        assertThat(result).hasSize(2);
        assertThat(result[0]).isEqualTo("vJrwpWtwJgWr");
        assertThat(result[1]).isEqualTo("hcsFMMfFFhFp");

        String[] result2 = rucksack.splitSack(TEST_INPUT.split("\n")[1]);
        assertThat(result2).hasSize(2);
        assertThat(result2[0]).isEqualTo("jqHRNqRjqzjGDLGL");
        assertThat(result2[1]).isEqualTo("rsFMfFZSrLrFZsSL");
    }

    @Test
    void canFindDuplicate() {
        assertThat(rucksack.findDuplicateInSack(rucksack.splitSack(TEST_INPUT.split("\n")[0]))).isEqualTo("p");
        assertThat(rucksack.findDuplicateInSack(rucksack.splitSack(TEST_INPUT.split("\n")[1]))).isEqualTo("L");
    }

    @Test
    void canGetPriority() {
        assertThat(rucksack.getPriority("a")).isEqualTo(1);
        assertThat(rucksack.getPriority("m")).isEqualTo(13);
        assertThat(rucksack.getPriority("z")).isEqualTo(26);
        assertThat(rucksack.getPriority("A")).isEqualTo(27);
        assertThat(rucksack.getPriority("L")).isEqualTo(38);
        assertThat(rucksack.getPriority("Z")).isEqualTo(52);
    }

    @Test
    void partOneTestInput() {
        assertThat(rucksack.totalPriorityOfSacks(TEST_INPUT)).isEqualTo(157);
    }

    @Test
    void partOne() throws Exception {
        String input = utils.readFileToString("2022/day03Input.txt");
        assertThat(rucksack.totalPriorityOfSacks(input)).isEqualTo(7763);
    }

    @Test
    void partTwoTestInput() {
        assertThat(rucksack.totalBadgePriority(TEST_INPUT)).isEqualTo(70);
    }

    @Test
    void partTwo() throws Exception {
        String input = utils.readFileToString("2022/day03Input.txt");
        assertThat(rucksack.totalBadgePriority(input)).isEqualTo(2569);
    }
}
