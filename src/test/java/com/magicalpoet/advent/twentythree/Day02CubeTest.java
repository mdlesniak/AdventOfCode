package com.magicalpoet.advent.twentythree;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day02CubeTest {
    private final TestUtils utils = new TestUtils();
    private String data;
    private Day02Cube cube;


    @BeforeEach
    void setup() throws Exception {
        data = utils.readFileToString("2023/day02Input.txt");
        cube = new Day02Cube();
    }

    @Test
    void determinesPossibility() {
        String game2 = "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue";
        String game3 = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";
        assertThat(cube.isPossible(game2)).isTrue();
        assertThat(cube.isPossible(game3)).isFalse();
    }

    @Test
    void addsGoodGamesTestData() {
        String input = """
                Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green""";
        assertThat(cube.totalGoodGames(input)).isEqualTo(8);
    }

    @Test
    void partOne() {
        assertThat(cube.totalGoodGames(data)).isEqualTo(2268);
    }

    @Test
    void calculatesPowerSet() {
        String game2 = "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue";
        String game3 = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";
        assertThat(cube.calculatePowerSet(game2)).isEqualTo(12);
        assertThat(cube.calculatePowerSet(game3)).isEqualTo(1560);
    }

    @Test
    void partTwo() {
        assertThat(cube.totalPowerSets(data)).isEqualTo(63542);
    }
}
