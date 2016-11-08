package com.torres.canvas.model;

import com.torres.canvas.model.exception.GeometryException;

import static com.torres.canvas.model.CanvasChars.GEOMETRY;
import static com.torres.canvas.model.exception.GeometryException.Type.NO_DIAGONAL_SUPPORTED;

/**
 * @author Javier Torres
 */
public class Line extends Geometry {
    public Line(final int x0, final int y0, final int x1, final int y1) throws GeometryException {
        super(x0, y0, x1, y1);

        if (x0 != x1 && y0 != y1) {
            throw new GeometryException(NO_DIAGONAL_SUPPORTED.getMessage());
        }
    }

    @Override
    public void draw(Character[][] canvas) {
        for (int iH=getY0(); iH<= getY1(); iH++) {
            for (int iW=getX0(); iW<= getX1(); iW++) {

                // Sets the line with the geometry default border
                canvas[iH][iW] = GEOMETRY.getValue();
            }
        }
    }
}
