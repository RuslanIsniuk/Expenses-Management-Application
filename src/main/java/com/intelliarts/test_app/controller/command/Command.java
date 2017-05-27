package com.intelliarts.test_app.controller.command;

import com.intelliarts.test_app.controller.exceptions.IncorrectCommandInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
    public abstract void execute(String enteredCommand);

    protected boolean checkCommand(String enteredCommand, String commandParser) throws IncorrectCommandInputException{
        Pattern pattern = Pattern.compile(commandParser);
        Matcher matcher = pattern.matcher(enteredCommand);
        return matcher.matches();
    }
}
