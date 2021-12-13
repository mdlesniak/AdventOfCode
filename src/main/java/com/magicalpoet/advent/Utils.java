package com.magicalpoet.advent;

import java.util.Arrays;

public class Utils {
    public int findMedian(int[] numbers) {
        Arrays.sort(numbers);
        return (numbers[numbers.length / 2] + numbers[(numbers.length - 1) / 2]) / 2;
    }

    public long findMedian(long[] numbers) {
        Arrays.sort(numbers);
        return (numbers[numbers.length / 2] + numbers[(numbers.length - 1) / 2]) / 2;
    }
}
