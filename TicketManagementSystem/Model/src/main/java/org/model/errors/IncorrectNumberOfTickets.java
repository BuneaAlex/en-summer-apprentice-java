package org.model.errors;

public class IncorrectNumberOfTickets extends Exception{

    public IncorrectNumberOfTickets() {
    }

    public IncorrectNumberOfTickets(String message) {
        super(message);
    }

    public IncorrectNumberOfTickets(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectNumberOfTickets(Throwable cause) {
        super(cause);
    }
}
