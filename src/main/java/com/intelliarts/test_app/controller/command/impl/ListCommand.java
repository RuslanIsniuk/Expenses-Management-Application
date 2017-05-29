package com.intelliarts.test_app.controller.command.impl;

import com.intelliarts.test_app.controller.command.Command;
import com.intelliarts.test_app.controller.exceptions.IncorrectCommandInputException;
import org.apache.log4j.Logger;

public class ListCommand extends Command {
    private static final Logger logger = Logger.getLogger(ListCommand.class);
    private static final String COMMAND_PARSER = "[l][i][s][t]";
    private static final String ERROR_MESSAGE = "Command not found!\nPerhaps you wanted to say \"list\" ?";

    @Override
    public void execute(String enteredCommand) {
        try {
            if (!checkCommand(enteredCommand, COMMAND_PARSER)) {
                throw new IncorrectCommandInputException(ERROR_MESSAGE);
            }
            //add service method
        } catch (IncorrectCommandInputException ie) {
            logger.info(ie);
        }
    }

    @Override
    public String[] getDataStrFromUserInput(String enteredCommand) {
        return enteredCommand.split("\\s", 0);
    }
}
