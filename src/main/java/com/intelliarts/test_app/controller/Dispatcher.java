package com.intelliarts.test_app.controller;


import com.intelliarts.test_app.controller.exceptions.CommandNotFoundException;

public class Dispatcher {

    public void commandIdentification(String enteredCommand) throws CommandNotFoundException{
        String commandSubStr = enteredCommand.substring(0, 3);

        switch (commandSubStr) {
            case "add":
                break;
            case "lis":
                break;
            case "cle":
                break;
            case "tot":
                break;
            case "hel":
                break;
            case "exi":
                break;
            default:
                throw new CommandNotFoundException();
        }
    }
}
