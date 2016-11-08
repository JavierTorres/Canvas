package com.torres.canvas.model;

/**
 * @author Javier Torres
 */
@FunctionalInterface
public interface CanvasConsumer {
    void accept(Canvas t) throws Exception;
}
