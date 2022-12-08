package com.magicalpoet.advent.twentytwo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
public class Day07DeviceSpace {

    // TODO: refactor to only traverse the terminal text once? we don't need the file lists in the end per reqs

    public Integer getSizeOfDirectoryToDelete(Map<String, Integer> filelist) {
        Map<String, Integer> directorySizes = getDirectorySizes(filelist);
        Integer freeSpace = 70000000 - directorySizes.get("");
        Integer spaceNeeded = 30000000 - freeSpace;
        return directorySizes.values().stream().sorted().filter(size -> size > spaceNeeded).findFirst().get();
    }

    public Long getTotalOfDirectoriesLessThan(Long threshhold, Map<String, Integer> filelist) {
        Map<String, Integer> dirSizes = getDirectorySizes(filelist);

        AtomicLong total = new AtomicLong(0L);
        dirSizes.forEach((dir, size) -> {
            if (size < threshhold) {
                total.addAndGet(size);
            }
        });
        return total.get();
    }
    public Map<String, Integer> getDirectorySizes(Map<String, Integer> filelist) {
        Map<String, Integer> dirSizes = new HashMap<>();
        filelist.forEach((filepath, value) -> {
            while (filepath.length() > 1) {
                filepath = filepath.substring(0, filepath.lastIndexOf("/"));
                if (dirSizes.containsKey(filepath)) {
                    dirSizes.put(filepath, dirSizes.get(filepath) + value);
                } else {
                    dirSizes.put(filepath, value);
                }
            }
        });
        return dirSizes;
    }

    public Map<String, Integer> getFilelist(String terminalText) {
        Map<String, Integer> filelist = new HashMap<>();
        String[] lines = terminalText.split("\n");
        StringBuilder currentDir = new StringBuilder("");
        for (String line : lines) {
            String[] parts = line.split(" ");
            if (parts[0].equals("$") && parts[1].equals("cd")) {
                currentDir = changeDirectory(currentDir, parts[2]);
            } else if (parts[0].matches("\\d.*")) {
                StringBuilder filename = new StringBuilder(currentDir.toString());
                if (!filename.toString().endsWith("/")) {
                    filename.append("/");
                }
                filelist.put(filename + parts[1], Integer.parseInt(parts[0]));
            }
        }
        return filelist;
    }

    private StringBuilder changeDirectory(StringBuilder currentDir, String arg) {
        if (arg.equals("..")) {
            currentDir = new StringBuilder(currentDir.substring(0, currentDir.lastIndexOf("/")));
        } else {
            if (!arg.equals("/") && (!currentDir.toString().equals("/"))) {
                currentDir.append("/");
            }
            currentDir.append(arg);
        }
        return currentDir;
    }

}
