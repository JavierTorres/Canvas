package com.torres.canvas.model;

import com.torres.canvas.model.exception.GeometryException;

import static com.torres.canvas.model.exception.GeometryException.Type.NO_LATEST_EQ_BIGGER;

/**
 * @author Javier Torres
 */
public abstract class Geometry implements Drawable {
    private final int x0;
    private final int x1;
    private final int y0;
    private final int y1;

    public Geometry(final int x0, final int y0, final int x1, final int y1) throws GeometryException {
        if (x1 < x0 || y1 < y0) {
            throw new GeometryException(NO_LATEST_EQ_BIGGER.getMessage());
        }

        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
    }

    public int getX0() {
        return x0;
    }

    public int getX1() {
        return x1;
    }

    public int getY0() {
        return y0;
    }

    public int getY1() {
        return y1;
    }
}
