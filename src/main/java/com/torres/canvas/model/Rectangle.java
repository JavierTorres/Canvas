package com.torres.canvas.model;

import com.torres.canvas.model.exception.GeometryException;

import static com.torres.canvas.model.CanvasChars.*;

/**
 * @author Javier Torres
 */
public class Rectangle extends Geometry {
    public Rectangle(final int x0, final int y0, final int x1, final int y1) throws GeometryException {
        super(x0, y0, x1, y1);
    }

    @Override
    public void draw(Character[][] canvas) {
        for (int iH=getY0(); iH<= getY1(); iH++) {
            for (int iW=getX0(); iW<= getX1(); iW++) {

                if (iH == getY0() || iH == getY1() || iW == getX0() || iW == getX1()) {
                    // Sets the border with the geometry default border
                    canvas[iH][iW] = GEOMETRY.getValue();

                } else {
                    // Sets empty character inside the rectangle
                    canvas[iH][iW] = EMPTY.getValue();
                }
            }
        }
    }
}
