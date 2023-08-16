package org.model.errors;

public class NoTicketsLeftException extends Exception{

    public NoTicketsLeftException() {
    }

    public NoTicketsLeftException(String message) {
        super(message);
    }

    public NoTicketsLeftException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoTicketsLeftException(Throwable cause) {
        super(cause);
    }
}
