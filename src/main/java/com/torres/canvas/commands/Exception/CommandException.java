package com.torres.canvas.commands.Exception;

/**
 * @author Javier Torres
 */
public class CommandException extends Exception{
    public CommandException(String msg) {
        super(msg);
    }

    public enum Type {
        REQUIRED("First you need to create the canvas"),
        INVALID_ARGUMENTS("Invalid arguments for the command executed");

        private final String message;

        Type(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
