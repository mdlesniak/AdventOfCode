package com.magicalpoet.advent.twentytwo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class Day08TreeHouse {
    private int forestWidth = 0;
    private int forestDepth = 0;

    public int findHighestScenicScore(String map) {
        List<Tree> trees = getTrees(map);
        int highestScore = 0;
        for (Tree tree : trees) {
            int scenicScore = calculateScenicScore(tree, trees);
            if (scenicScore > highestScore) {
                highestScore = scenicScore;
            }
        }
        return highestScore;
    }

    private int calculateScenicScore(Tree tree, List<Tree> trees) {
        int visibleNorth = countVisibleNorth(tree, trees);
        int visibleSouth = countVisibleSouth(tree, trees);
        int visibleEast = countVisibleEast(tree, trees);
        int visibleWest = countVisibleWest(tree, trees);
        return visibleEast * visibleNorth * visibleWest * visibleSouth;
    }

    private int countVisibleNorth(Tree tree, List<Tree> trees) {
        List<Tree> treesNorth = new ArrayList<>(trees.stream()
                .filter(t -> t.getX() == tree.getX() && t.getY() < tree.getY())
                .toList());
        treesNorth.sort(Comparator.comparingInt(Tree::getY));
        int count = 0;
        for (int i = treesNorth.size() - 1; i >= 0; i--) {
            if (treesNorth.get(i).getHeight() < tree.getHeight()) {
                count++;
            } else {
                if (treesNorth.get(i).getHeight() == tree.getHeight()) {
                    count++;
                }
                break;
            }
        }
        return count;
    }

    private int countVisibleSouth(Tree tree, List<Tree> trees) {
        List<Tree> treesSouth = new ArrayList<>(trees.stream()
                .filter(t -> t.getX() == tree.getX() && t.getY() > tree.getY())
                .toList());
        treesSouth.sort(Comparator.comparingInt(Tree::getY));
        int count = 0;
        for (Tree value : treesSouth) {
            if (value.getHeight() < tree.getHeight()) {
                count++;
            } else {
                if (value.getHeight() == tree.getHeight()) {
                    count++;
                }
                break;
            }
        }
        return count;
    }

    private int countVisibleEast(Tree tree, List<Tree> trees) {
        List<Tree> treesEast = new ArrayList<>(trees.stream()
                .filter(t -> t.getX() > tree.getX() && t.getY() == tree.getY())
                .toList());
        treesEast.sort(Comparator.comparingInt(Tree::getX));
        int count = 0;
        for (Tree value : treesEast) {
            if (value.getHeight() < tree.getHeight()) {
                count++;
            } else {
                if (value.getHeight() == tree.getHeight()) {
                    count++;
                }
                break;
            }
        }
        return count;
    }

    private int countVisibleWest(Tree tree, List<Tree> trees) {
        List<Tree> treesWest = new ArrayList<>(trees.stream()
                .filter(t -> t.getX() < tree.getX() && t.getY() == tree.getY())
                .toList());
        treesWest.sort(Comparator.comparingInt(Tree::getX));
        int count = 0;
        for (int i = treesWest.size() - 1; i >= 0; i--) {
            if (treesWest.get(i).getHeight() < tree.getHeight()) {
                count++;
            } else {
                if (treesWest.get(i).getHeight() == tree.getHeight()) {
                    count++;
                }
                break;
            }
        }
        return count;
    }

    public int findVisibleTrees(String map) {
        List<Tree> trees = getTrees(map);
        int countOfVisible = 0;
        for (Tree tree : trees) {
            if (isVisible(tree, trees)) {
                countOfVisible++;
            }
        }
        return countOfVisible;
    }

    private boolean isVisible(Tree tree, List<Tree> trees) {
        if (tree.getX() == 0 || tree.getY() == 0 || tree.getX() == forestWidth || tree.getY() == forestDepth) {
            return true;
        }

        List<Tree> blockersNorth = trees.stream()
                .filter(t -> t.getX() == tree.getX() && t.getY() < tree.getY() && t.getHeight() >= tree.getHeight())
                .toList();
        if (blockersNorth.isEmpty()) {
            return true;
        }

        List<Tree> blockersWest = trees.stream()
                .filter(t -> t.getX() < tree.getX() && t.getY() == tree.getY() && t.getHeight() >= tree.getHeight())
                .toList();
        if (blockersWest.isEmpty()) {
            return true;
        }

        List<Tree> blockersEast = trees.stream()
                .filter(t -> t.getX() > tree.getX() && t.getY() == tree.getY() && t.getHeight() >= tree.getHeight())
                .toList();
        if (blockersEast.isEmpty()) {
            return true;
        }

        List<Tree> blockersSouth = trees.stream()
                .filter(t -> t.getX() == tree.getX() && t.getY() > tree.getY() && t.getHeight() >= tree.getHeight())
                .toList();
        return blockersSouth.isEmpty();
    }

    private List<Tree> getTrees(String map) {
        String[] rows = map.split("\n");
        forestDepth = rows.length - 1;
        forestWidth = rows[0].length() - 1;
        List<Tree> trees = new ArrayList<>();
        for (int i = 0; i < rows.length; i++) {
            int[] ints = Arrays.stream(rows[i].split("")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < ints.length; j++) {
                trees.add(new Tree(j, i, ints[j]));
            }
        }
        return trees;
    }


    @AllArgsConstructor
    @Getter
    @Setter
    private class Tree {
        private int x;
        private int y;
        private int height;
    }
}
