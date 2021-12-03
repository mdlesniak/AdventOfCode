package com.magicalpoet.advent;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.List;

public class Utils {
    protected List<String> readFileToList(String filename) throws IOException {
        return IOUtils.readLines(this.getClass().getClassLoader().getResourceAsStream(filename), "utf-8");
    }
}
