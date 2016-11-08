package com.torres.canvas.model;

/**
 * @author Javier Torres
 */
public enum CanvasChars {
    HORIZONTAL_BORDER('-'),
    VERTICAL_BORDER('|'),
    GEOMETRY('x'),
    EMPTY(' '),
    NEW_LINE('\n');

    private final Character value;

    CanvasChars(Character value) {
        this.value = value;
    }

    public Character getValue() {
        return value;
    }
}
