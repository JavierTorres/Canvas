package com.torres.canvas.model;

import com.torres.canvas.model.exception.GeometryException;
import org.junit.Test;

import static com.torres.canvas.model.CanvasChars.GEOMETRY;
import static org.junit.Assert.assertTrue;

/**
 * Created by javierbracerotorres on 31/10/2016.
 */
public class LineTest {


    @Test(expected = GeometryException.class)
    public void testDiagonalLineNotSupported() throws GeometryException {
        Line line = new Line(2, 3, 4, 5);
    }

    @Test
    public void testDrawHorizontal() throws GeometryException {
        Line line = new Line(1, 1, 2, 1);
        Character[][] canvas = new Character[3][3];
        line.draw(canvas);
        assertTrue(GEOMETRY.getValue().equals(canvas[1][1]));
        assertTrue(GEOMETRY.getValue().equals(canvas[1][2]));
    }

    @Test
    public void testDrawVertical() throws GeometryException {
        Line line = new Line(1, 1, 1, 2);
        Character[][] canvas = new Character[3][3];
        line.draw(canvas);
        assertTrue(GEOMETRY.getValue().equals(canvas[1][1]));
        assertTrue(GEOMETRY.getValue().equals(canvas[2][1]));
    }

    @Test(expected = GeometryException.class)
    public void testLatestPostBigger() throws GeometryException {
        Line r = new Line(0, 0, 2, 2);
    }
}
