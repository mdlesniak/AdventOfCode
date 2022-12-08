package com.magicalpoet.advent.twentytwo;

import com.magicalpoet.advent.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class Day07DeviceSpaceTest {
    private final TestUtils utils = new TestUtils();
    private Day07DeviceSpace deviceSpace;
    private String testInput;
    private String realInput;

    @BeforeEach
    void setUp() throws Exception {
        deviceSpace = new Day07DeviceSpace();
        testInput = utils.readFileToString("2022/day07TestInput.txt");
        realInput = utils.readFileToString("2022/day07Input.txt");
    }

    @Test
    void canGetFilelist() {
        Map<String, Integer> filelist = deviceSpace.getFilelist(testInput);
        assertThat(filelist).containsKeys(
                "/a/e/i", "/a/f", "/a/g", "/a/h.lst", "/b.txt", "/c.dat", "/d/j", "/d/d.log", "/d/d.ext", "/d/k"
        );
    }

    @Test
    void partOne_sampleInput() {
        Map<String, Integer> filelist = deviceSpace.getFilelist(testInput);
        Long total = deviceSpace.getTotalOfDirectoriesLessThan(100000L, filelist);
        assertThat(total).isEqualTo(95437L);
    }

    @Test
    void partOne() {
        Map<String, Integer> filelist = deviceSpace.getFilelist(realInput);
        Long total = deviceSpace.getTotalOfDirectoriesLessThan(100000L, filelist);
        assertThat(total).isEqualTo(1886043L);
    }

    @Test
    void getRootDirSize_sampleInput() {
        Map<String, Integer> filelist = deviceSpace.getFilelist(testInput);
        assertThat(deviceSpace.getDirectorySizes(filelist)).containsEntry("", 48381165);
    }

    @Test
    void partTwo_sampleInput() {
        Map<String, Integer> filelist = deviceSpace.getFilelist(testInput);
        assertThat(deviceSpace.getSizeOfDirectoryToDelete(filelist)).isEqualTo(24933642);
    }

    @Test
    void partTwo() {
        Map<String, Integer> filelist = deviceSpace.getFilelist(realInput);
        assertThat(deviceSpace.getSizeOfDirectoryToDelete(filelist)).isEqualTo(3842121);
    }
}
