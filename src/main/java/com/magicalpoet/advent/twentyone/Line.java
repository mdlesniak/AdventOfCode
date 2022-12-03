package com.magicalpoet.advent.twentyone;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Line {
    protected Coordinate start;
    protected Coordinate end;

    public boolean isVertical() {
        return start.x == end.x;
    }

    public boolean isHorizontal() {
        return start.y == end.y;
    }
}
