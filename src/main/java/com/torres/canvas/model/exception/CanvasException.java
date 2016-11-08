package com.torres.canvas.model.exception;

/**
 * @author Javier Torres
 */
public class CanvasException extends Exception {
    public CanvasException(String message) {
        super(message);
    }


    public enum Type {
        NOT_MINIMUM("The minimum width and height has to be 1"),
        NOT_IN_CANVAS("It's not inside the canvas area");

        private final String message;

        Type(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }


}
