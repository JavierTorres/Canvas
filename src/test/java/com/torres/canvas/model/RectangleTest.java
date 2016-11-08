package com.torres.canvas.model;

import com.torres.canvas.model.exception.GeometryException;
import org.junit.Test;

import static com.torres.canvas.model.CanvasChars.EMPTY;
import static com.torres.canvas.model.CanvasChars.GEOMETRY;
import static org.junit.Assert.assertTrue;

/**
 * Created by javierbracerotorres on 31/10/2016.
 */
public class RectangleTest {

    @Test
    public void testDraw() throws GeometryException {
        Character[][] canvas = new Character[4][4];
        Rectangle r = new Rectangle(0, 0, 2, 2);
        r.draw(canvas);
        assertTrue(GEOMETRY.getValue().equals(canvas[0][0]));
        assertTrue(GEOMETRY.getValue().equals(canvas[0][1]));
        assertTrue(GEOMETRY.getValue().equals(canvas[0][2]));

        assertTrue(GEOMETRY.getValue().equals(canvas[1][0]));
        assertTrue(EMPTY.getValue().equals(canvas[1][1]));
        assertTrue(GEOMETRY.getValue().equals(canvas[1][2]));

        assertTrue(GEOMETRY.getValue().equals(canvas[2][0]));
        assertTrue(GEOMETRY.getValue().equals(canvas[2][1]));
        assertTrue(GEOMETRY.getValue().equals(canvas[2][2]));
    }

    @Test(expected = GeometryException.class)
    public void testLatestPostBigger() throws GeometryException {
        Rectangle r = new Rectangle(2, 2, 0, 0);
    }
}
