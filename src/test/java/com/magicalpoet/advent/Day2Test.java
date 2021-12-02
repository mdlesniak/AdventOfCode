package com.magicalpoet.advent;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {

    @Test
    void getDepthSimple() {
        Day2 day2 = new Day2();
        int depth = day2.findAnswer();
        assertThat(depth).isEqualTo(1);
    }
}
