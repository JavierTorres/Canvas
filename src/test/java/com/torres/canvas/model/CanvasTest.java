package com.torres.canvas.model;

import com.torres.canvas.model.exception.CanvasException;
import com.torres.canvas.model.exception.GeometryException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by javierbracerotorres on 31/10/2016.
 */
public class CanvasTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test(expected = CanvasException.class)
    public void testZeroSize() throws CanvasException {
        Canvas c = new Canvas(0, 0);
    }

    @Test(expected = CanvasException.class)
    public void testNegativeSize() throws CanvasException {
        Canvas c = new Canvas(-1, -1);
    }

    @Test
    public void testPrint() throws CanvasException {
        Canvas c = new Canvas(10, 5);
        c.print();
        assertEquals(
                        "------------\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "------------\n", outContent.toString());
    }

    @Test(expected = CanvasException.class)
    public void testAddGeometryOutsideCanvas() throws CanvasException, GeometryException {
        Canvas c = new Canvas(10, 5);
        Line l = new Line(20, 20, 20, 40);
        c.addGeometry(l);
    }

    @Test(expected = CanvasException.class)
    public void testAddGeometryAtTheBorder() throws CanvasException, GeometryException {
        Canvas c = new Canvas(10, 5);
        Line l = new Line(0, 0, 0, 3);
        c.addGeometry(l);
    }

    @Test(expected = CanvasException.class)
    public void testFillOutsideCanvas() throws CanvasException, GeometryException {
        Canvas c = new Canvas(10, 5);
        c.addFilling(20, 20, '*');
    }

    @Test(expected = CanvasException.class)
    public void testFillAtTheBorder() throws CanvasException, GeometryException {
        Canvas c = new Canvas(10, 5);
        c.addFilling(0, 0, '*');
    }
}
