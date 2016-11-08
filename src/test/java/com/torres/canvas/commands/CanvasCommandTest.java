package com.torres.canvas.commands;

import com.torres.canvas.commands.Exception.CommandException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.CommandResult;
import org.springframework.shell.core.JLineShellComponent;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CanvasCommandTest {

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

    @Test
    public void testCreateCanvas() {
        Bootstrap bootstrap = new Bootstrap();

        JLineShellComponent shell = bootstrap.getJLineShellComponent();

        CommandResult cr = shell.executeCommand("C 10 5");
        assertEquals(true, cr.isSuccess());
        assertEquals(
                "------------\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "------------\n", outContent.toString());
    }

    @Test
    public void testCreateCanvasMissingOneArgument() {
        Bootstrap bootstrap = new Bootstrap();
        JLineShellComponent shell = bootstrap.getJLineShellComponent();
        shell.executeCommand("C 10");
        assertTrue(errContent.toString().contains(CommandException.Type.INVALID_ARGUMENTS.getMessage()));
    }

    @Test
    public void testCreateCanvasErrorArgumentType() {
        Bootstrap bootstrap = new Bootstrap();
        JLineShellComponent shell = bootstrap.getJLineShellComponent();
        shell.executeCommand("C r t");
        assertTrue(errContent.toString().contains(CommandException.Type.INVALID_ARGUMENTS.getMessage()));
    }

    @Test
    public void testOverrideCanvas() {
        Bootstrap bootstrap = new Bootstrap();

        JLineShellComponent shell = bootstrap.getJLineShellComponent();

        CommandResult cr = shell.executeCommand("C 10 5");
        assertEquals(true, cr.isSuccess());
        cr = shell.executeCommand("C 5 3");
        assertEquals(true, cr.isSuccess());
        assertEquals(
                        "------------\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "------------\n" +
                        "-------\n" +
                        "|     |\n" +
                        "|     |\n" +
                        "|     |\n" +
                        "-------\n", outContent.toString());
    }

    @Test
    public void testSomethingBeforeCanvas() {
        Bootstrap bootstrap = new Bootstrap();

        JLineShellComponent shell = bootstrap.getJLineShellComponent();

        shell.executeCommand("L 2 5 3 3");
        shell.executeCommand("R 2 5 3 3");
        shell.executeCommand("B 2 5 o");
        assertTrue(errContent.toString().contains("First you need to create the canvas\n" +
                "First you need to create the canvas\n" +
                "First you need to create the canvas\n"));
    }

    @Test
    public void testCreateLine() {
        Bootstrap bootstrap = new Bootstrap();

        JLineShellComponent shell = bootstrap.getJLineShellComponent();

        CommandResult cr = shell.executeCommand("C 10 5");
        assertEquals(true, cr.isSuccess());
        cr = shell.executeCommand("L 2 3 5 3");
        assertEquals(true, cr.isSuccess());
        assertEquals(
                        // Created canvas
                        "------------\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "------------\n" +
                        // Created line
                        "------------\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "| xxxx     |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "------------\n"
                , outContent.toString());
    }

    @Test
    public void testCreateRectangle() {

        Bootstrap bootstrap = new Bootstrap();

        JLineShellComponent shell = bootstrap.getJLineShellComponent();

        CommandResult cr = shell.executeCommand("C 10 5");
        assertEquals(true, cr.isSuccess());
        cr = shell.executeCommand("R 2 2 6 4");
        assertEquals(true, cr.isSuccess());
        assertEquals(
                        // Created canvas
                        "------------\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "------------\n" +
                        // Created rectangle
                        "------------\n" +
                        "|          |\n" +
                        "| xxxxx    |\n" +
                        "| x   x    |\n" +
                        "| xxxxx    |\n" +
                        "|          |\n" +
                        "------------\n"
                , outContent.toString());
    }

    @Test
    public void testFill() {

        Bootstrap bootstrap = new Bootstrap();

        JLineShellComponent shell = bootstrap.getJLineShellComponent();

        CommandResult cr = shell.executeCommand("C 10 5");
        assertEquals(true, cr.isSuccess());
        cr = shell.executeCommand("B 5 3 o");
        assertEquals(true, cr.isSuccess());
        assertEquals(
                        // Created canvas
                        "------------\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "|          |\n" +
                        "------------\n" +
                        // Filled the canvas
                        "------------\n" +
                        "|oooooooooo|\n" +
                        "|oooooooooo|\n" +
                        "|oooooooooo|\n" +
                        "|oooooooooo|\n" +
                        "|oooooooooo|\n" +
                        "------------\n"
                , outContent.toString());
    }

    @Test
    public void testAll() {

        Bootstrap bootstrap = new Bootstrap();

        JLineShellComponent shell = bootstrap.getJLineShellComponent();

        CommandResult cr = shell.executeCommand("C 40 15");
        assertEquals(true, cr.isSuccess());

        // Horizontal line
        cr = shell.executeCommand("L 1 7 15 7");
        assertEquals(true, cr.isSuccess());
        // Vertical line
        cr = shell.executeCommand("L 15 1 15 7");
        assertEquals(true, cr.isSuccess());
        // Rectangle overriding some positions of previous lines
        cr = shell.executeCommand("R 10 4 30 10");
        assertEquals(true, cr.isSuccess());
        // Fill the rectangle background
        cr = shell.executeCommand("B 12 7 R");
        assertEquals(true, cr.isSuccess());
        // Fill the canvas background
        cr = shell.executeCommand("B 20 13 o");
        assertEquals(true, cr.isSuccess());
        assertEquals(
                        // Created canvas
                        "------------------------------------------\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "------------------------------------------\n" +
                         // Created a horizontal line
                        "------------------------------------------\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|xxxxxxxxxxxxxxx                         |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "------------------------------------------\n" +
                         // Created a vertical line
                        "------------------------------------------\n" +
                        "|              x                         |\n" +
                        "|              x                         |\n" +
                        "|              x                         |\n" +
                        "|              x                         |\n" +
                        "|              x                         |\n" +
                        "|              x                         |\n" +
                        "|xxxxxxxxxxxxxxx                         |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "------------------------------------------\n" +
                         // Created a rectangle overriding the lines
                        "------------------------------------------\n" +
                        "|              x                         |\n" +
                        "|              x                         |\n" +
                        "|              x                         |\n" +
                        "|         xxxxxxxxxxxxxxxxxxxxx          |\n" +
                        "|         x                   x          |\n" +
                        "|         x                   x          |\n" +
                        "|xxxxxxxxxx                   x          |\n" +
                        "|         x                   x          |\n" +
                        "|         x                   x          |\n" +
                        "|         xxxxxxxxxxxxxxxxxxxxx          |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "------------------------------------------\n" +
                         // Filling the rectangle with R
                        "------------------------------------------\n" +
                        "|              x                         |\n" +
                        "|              x                         |\n" +
                        "|              x                         |\n" +
                        "|         xxxxxxxxxxxxxxxxxxxxx          |\n" +
                        "|         xRRRRRRRRRRRRRRRRRRRx          |\n" +
                        "|         xRRRRRRRRRRRRRRRRRRRx          |\n" +
                        "|xxxxxxxxxxRRRRRRRRRRRRRRRRRRRx          |\n" +
                        "|         xRRRRRRRRRRRRRRRRRRRx          |\n" +
                        "|         xRRRRRRRRRRRRRRRRRRRx          |\n" +
                        "|         xxxxxxxxxxxxxxxxxxxxx          |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "|                                        |\n" +
                        "------------------------------------------\n" +
                         // Filling the background with o
                        "------------------------------------------\n" +
                        "|              xooooooooooooooooooooooooo|\n" +
                        "|              xooooooooooooooooooooooooo|\n" +
                        "|              xooooooooooooooooooooooooo|\n" +
                        "|         xxxxxxxxxxxxxxxxxxxxxoooooooooo|\n" +
                        "|         xRRRRRRRRRRRRRRRRRRRxoooooooooo|\n" +
                        "|         xRRRRRRRRRRRRRRRRRRRxoooooooooo|\n" +
                        "|xxxxxxxxxxRRRRRRRRRRRRRRRRRRRxoooooooooo|\n" +
                        "|oooooooooxRRRRRRRRRRRRRRRRRRRxoooooooooo|\n" +
                        "|oooooooooxRRRRRRRRRRRRRRRRRRRxoooooooooo|\n" +
                        "|oooooooooxxxxxxxxxxxxxxxxxxxxxoooooooooo|\n" +
                        "|oooooooooooooooooooooooooooooooooooooooo|\n" +
                        "|oooooooooooooooooooooooooooooooooooooooo|\n" +
                        "|oooooooooooooooooooooooooooooooooooooooo|\n" +
                        "|oooooooooooooooooooooooooooooooooooooooo|\n" +
                        "|oooooooooooooooooooooooooooooooooooooooo|\n" +
                        "------------------------------------------\n"
                , outContent.toString());
    }

}
