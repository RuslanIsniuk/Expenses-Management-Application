package com.intelliarts.test_app.model.exceptions;

public class NoDatesFoundException extends Exception {
    public NoDatesFoundException() {
        super();
    }

    public NoDatesFoundException(String errorMessage) {
        super(errorMessage);
    }
}
