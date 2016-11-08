# CANVAS - Javier Bracero Torres
## Description
The following project contains a Canvas command line execution allowing the following actions:

### Canvas
* command line: C w h
* Creates a new canvas of width w and height h.

### Line
* command line: L x1 y1 x2 y2
* Creates a new line from (x1, y1) to (x2, y2). Only horizontal or vertical lines are supported. Horizontal and vertical lines will be drawn using the character x.

### Rectangle
* command line: R x1 y1 x2 y2
* Creates a new rectangle, whose upper left corner is (x1, y1) and lower right corner is (x2, y2). Horizontal and vertical lines will be drawn using the character x.

### Fill
* command line: B x y c
* Fills the entire area connected to (x,y) with colour c.

### Quit
* command line: q
* Quite the program

### Help
* command line: help
* Help menu

## Requirements
* Java 8
* Maven

## Libraries
* Junit version 4.10: Used to implement unit test.
* Spring-shell 1.2.0.M1: Used to provide facilities to use the command line interactivity and focus on the business problem.