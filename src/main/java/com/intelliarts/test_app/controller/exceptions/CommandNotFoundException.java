package com.intelliarts.test_app.controller.exceptions;

public class CommandNotFoundException extends Exception {
    public CommandNotFoundException() {
        super();
    }

    public CommandNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
