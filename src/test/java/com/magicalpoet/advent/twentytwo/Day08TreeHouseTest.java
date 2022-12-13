package com.magicalpoet.advent.twentytwo;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Day08TreeHouseTest {
    private final TestUtils utils = new TestUtils();
    private Day08TreeHouse treeHouse;
    private String testInput;
    private String realInput;

    @BeforeEach
    void setUp() throws Exception {
        treeHouse = new Day08TreeHouse();
        testInput = """
                30373
                25512
                65332
                33549
                35390""";
        realInput = utils.readFileToString("2022/day08Input.txt");
    }

    @Test
    void canCountVisible_simpleCase() {
        assertThat(treeHouse.findVisibleTrees("30373".split(""))).isEqualTo(5);
        assertThat(treeHouse.findVisibleTrees("303".split(""))).isEqualTo(3);
        assertThat(treeHouse.findVisibleTrees("302345678373".split(""))).isEqualTo(12);
    }
}
