package com.magicalpoet.advent;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Map;
@UtilityClass
public class Utils {

    public int findMedian(int[] numbers) {
        Arrays.sort(numbers);
        return (numbers[numbers.length / 2] + numbers[(numbers.length - 1) / 2]) / 2;
    }

    public long findMedian(long[] numbers) {
        Arrays.sort(numbers);
        return (numbers[numbers.length / 2] + numbers[(numbers.length - 1) / 2]) / 2;
    }

    public void incrementMapAtKey(Map<String, Integer> map, String key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }
}
