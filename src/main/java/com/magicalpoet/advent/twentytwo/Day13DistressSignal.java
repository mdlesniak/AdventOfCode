package com.magicalpoet.advent.twentytwo;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Day13DistressSignal {


    public List<Integer> createPacket(String input) {

        if (input.startsWith("[")) {
            String packet = StringUtils.substringBetween(input, "[", "]");
        } else {
            String[] numsArray = input.split(",");
        }
//        return Arrays.stream(numsArray).mapToInt(Integer::parseInt).boxed().toList();
        return Lists.newArrayList();
    }
}
