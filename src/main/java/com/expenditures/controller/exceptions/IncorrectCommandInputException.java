package com.expenditures.controller.exceptions;

public class IncorrectCommandInputException extends CommandNotFoundException{
    public IncorrectCommandInputException() {
        super();
    }

    public IncorrectCommandInputException(String errorMessage) {
        super(errorMessage);
    }
}
