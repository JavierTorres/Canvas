package com.torres.canvas.model;

import static com.torres.canvas.model.CanvasChars.*;

/**
 * @author Javier Torres
 */
public class Filler implements Drawable {
    private final int x;
    private final int y;
    private final Character c;
    private Character[][] canvas;

    public Filler(int x, int y, Character c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    @Override
    public void draw(Character[][] canvas) {
        this.canvas = canvas;
        fLeftTopBottom(x, y);
        fRightTopBottom(x, y);
        fTopOnlyLeftOnlyRight(x, y);
        fBottomOnlyLeftOnlyRight(x, y);
    }


    /**
     * Fills recursive left, top and bottom.
     *
     * @param xPos
     * @param yPos
     */
    private void fLeftTopBottom(int xPos, int yPos) {
        if (!next(xPos, yPos)) {
            return;
        }

        fTopOnlyLeftOnlyRight(xPos, yPos - 1);
        fBottomOnlyLeftOnlyRight(xPos, yPos + 1);
        fLeftTopBottom(xPos - 1, yPos);
    }

    /**
     * Fills recursive right, top and bottom.
     *
     * @param xPos
     * @param yPos
     */
    private void fRightTopBottom(int xPos, int yPos) {
        if (!next(xPos, yPos)) {
            return;
        }

        fTopOnlyLeftOnlyRight(xPos, yPos - 1);
        fBottomOnlyLeftOnlyRight(xPos, yPos + 1);
        fRightTopBottom(xPos + 1, yPos);
    }

    /**
     * Fills recursive top and only left and only right.
     *
     * @param xPos
     * @param yPos
     */
    private void fTopOnlyLeftOnlyRight(int xPos, int yPos) {
        if (!next(xPos, yPos)) {
            return;
        }

        fOnlyLeft(xPos - 1, yPos);
        fOnlyRight(xPos + 1, yPos);
        fTopOnlyLeftOnlyRight(xPos, yPos - 1);
    }

    /**
     * Fills recursive bottom and only left and only right.
     *
     * @param xPos
     * @param yPos
     */
    private void fBottomOnlyLeftOnlyRight(int xPos, int yPos) {
        if (!next(xPos, yPos)) {
            return;
        }

        fOnlyLeft(xPos - 1, yPos);
        fOnlyRight(xPos + 1, yPos);
        fBottomOnlyLeftOnlyRight(xPos, yPos + 1);
    }

    /**
     * Fills recursive only left.
     *
     * @param xPos
     * @param yPos
     */
    private void fOnlyLeft(int xPos, int yPos) {
        if (!next(xPos, yPos)) {
            return;
        }

        fOnlyLeft(xPos - 1, yPos);
    }

    /**
     * Fills recursive only right.
     *
     * @param xPos
     * @param yPos
     */
    private void fOnlyRight(int xPos, int yPos) {
        if (!next(xPos, yPos)) {
            return;
        }

        fOnlyRight(xPos + 1, yPos);
    }

    /**
     * If the next value contains a geometry, horizontal or vertical character returns false.
     * Otherwise assign the position to the specific colour c and returns true.
     *
     * @param xPos
     * @param yPos
     * @return
     */
    private boolean next(int xPos, int yPos) {
        if (canvas[yPos][xPos] == GEOMETRY.getValue() ||
                canvas[yPos][xPos] == HORIZONTAL_BORDER.getValue() ||
                canvas[yPos][xPos] == VERTICAL_BORDER.getValue()) {
            return false;
        }

        canvas[yPos][xPos] = c;
        return true;
    }
}
