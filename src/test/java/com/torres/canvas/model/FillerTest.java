package com.torres.canvas.model;

import com.torres.canvas.model.exception.GeometryException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by javierbracerotorres on 31/10/2016.
 */
public class FillerTest {
    private final Character DEFAULT_COLOUR = '*';

    @Test
    public void testFill() throws GeometryException {
        Filler f = new Filler(2, 2, DEFAULT_COLOUR);
        Character[][] c = new Character[6][6];
        Rectangle r = new Rectangle(0, 0, 4, 4);
        r.draw(c);
        f.draw(c);

        assertTrue(DEFAULT_COLOUR.equals(c[1][1]));
        assertTrue(DEFAULT_COLOUR.equals(c[1][2]));
        assertTrue(DEFAULT_COLOUR.equals(c[1][3]));

        assertTrue(DEFAULT_COLOUR.equals(c[2][1]));
        assertTrue(DEFAULT_COLOUR.equals(c[2][2]));
        assertTrue(DEFAULT_COLOUR.equals(c[2][3]));

        assertTrue(DEFAULT_COLOUR.equals(c[3][1]));
        assertTrue(DEFAULT_COLOUR.equals(c[3][2]));
        assertTrue(DEFAULT_COLOUR.equals(c[3][3]));
    }
}
