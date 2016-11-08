package com.torres.canvas.model;

import com.torres.canvas.model.exception.CanvasException;

import static com.torres.canvas.model.CanvasChars.*;
import static com.torres.canvas.model.exception.CanvasException.Type.*;

/**
 * @author Javier Torres
 */
public class Canvas {

    private final int width;
    private final int height;

    private Character[][] canvas;

    private static final int WIDTH_POSITIONS_RESERVED = 3; // Left/Right border and \n
    private static final int HEIGHT_POSITIONS_RESERVED = 2; // Top/Bottom border

    /**
     * Creates a new Canvas.
     *
     * @param width
     * @param height
     * @throws CanvasException when the area is minimum required.
     */
    public Canvas(int width, int height) throws CanvasException {
        if (width < 1 || height < 1) {
            throw new CanvasException(NOT_MINIMUM.getMessage());
        }

        this.width = width + WIDTH_POSITIONS_RESERVED;
        this.height = height + HEIGHT_POSITIONS_RESERVED;
        canvas = new Character[this.height][this.width];
        draw();
    }

    /**
     * Initialize the canvas with the borders and the empty values.
     */
    private void draw() {
        for (int iH=0; iH< height; iH++) {
            for (int iW=0; iW< width; iW++) {

                if (iH == 0 || iH == (height - 1)) {
                    canvas[iH][iW] = HORIZONTAL_BORDER.getValue();

                } else if (iW == 0 || iW == (width - 2)) {
                    canvas[iH][iW] = VERTICAL_BORDER.getValue();

                } else {
                    canvas[iH][iW] = EMPTY.getValue();
                }
            }

            canvas[iH][width-1] = NEW_LINE.getValue();
        }
    }

    /**
     * Prints the canvas including all the geometries and the fillings.
     */
    public void print() {
        for (int iH=0; iH< height; iH++) {
            for (int iW=0; iW< width; iW++) {
                System.out.print(canvas[iH][iW]);
            }
        }
    }

    /**
     * Add a Geometry to the canvas.
     * @param geometry
     * @throws CanvasException when the geometry is not inside the canvas area.
     */
    public void addGeometry(Geometry geometry) throws CanvasException {
        if (geometry.getX0() > width || geometry.getX1() > width || geometry.getY0() > height || geometry.getY1() > height ||
                geometry.getX0() < 1 || geometry.getX1() < 1 || geometry.getY0() < 1 || geometry.getY1() < 1) {
            notInCanvasArea();
        }

        geometry.draw(canvas);
    }

    /**
     * Fills the area contained by x, y using the specific colour c.
     * @param x
     * @param y
     * @param c
     * @throws CanvasException when the filling is not inside the canvas area.
     */
    public void addFilling(int x, int y, Character c) throws CanvasException {
        if (x > width || y > height || x < 1 || y < 1)  {
            notInCanvasArea();
        }

        new Filler(x, y, c).draw(canvas);
    }

    /**
     * Throw a CanvasException containing the NOT_IN_CANVAS message.
     * @throws CanvasException
     */
    private void notInCanvasArea() throws CanvasException {
        throw new CanvasException(NOT_IN_CANVAS.getMessage());
    }
}
