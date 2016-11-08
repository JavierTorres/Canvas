package com.torres.canvas.model.exception;

/**
 * @author Javier Torres
 */
public class GeometryException extends Exception {
    public GeometryException(String message) {
        super(message);
    }

    public enum Type {
        NO_DIAGONAL_SUPPORTED("Only vertical or horizontal lines are supported"),
        NO_LATEST_EQ_BIGGER("The last positions should be bigger or equals to the initials positions");

        private final String message;

        Type(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
