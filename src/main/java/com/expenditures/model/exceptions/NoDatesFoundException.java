package com.expenditures.model.exceptions;

public class NoDatesFoundException extends Exception {
    public NoDatesFoundException() {
        super();
    }

    public NoDatesFoundException(String errorMessage) {
        super(errorMessage);
    }
}
