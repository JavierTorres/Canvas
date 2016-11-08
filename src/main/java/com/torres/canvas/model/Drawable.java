package com.torres.canvas.model;

/**
 * @author Javier Torres
 */
public interface Drawable {
    /**
     * Defines how to draw in the specific canvas
     *
     * @param canvas
     */
    void draw(Character[][] canvas);
}
