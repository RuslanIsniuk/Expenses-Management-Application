package com.intelliarts.test_app.controller.command.impl;

import com.intelliarts.test_app.controller.command.Command;
import com.intelliarts.test_app.controller.exceptions.IncorrectCommandInputException;
import org.apache.log4j.Logger;

public class TotalCommand extends Command {
    private static final Logger logger = Logger.getLogger(TotalCommand.class);
    private static final String COMMAND_PARSER = "[t][o][t][a][l]\\s[A-Z]{3}";
    private static final String ERROR_MESSAGE = "Command not found!\nPerhaps you wanted to say \"total CUR\" ?";
    private String[] userInput;

    @Override
    public void execute(String enteredCommand) {
        try {
            if (!checkCommand(enteredCommand, COMMAND_PARSER)) {
                throw new IncorrectCommandInputException(ERROR_MESSAGE);
            }

            userInput = getDataStrFromUserInput(enteredCommand);

            if (isCurrencyTypeIncorrect(userInput[1])) {
                throw new IncorrectCommandInputException(ERROR_MESSAGE);
            }
            //add service method
        } catch (IncorrectCommandInputException ie) {
            logger.info(ie);
        }
    }

    @Override
    public String[] getDataStrFromUserInput(String enteredCommand) {
        return enteredCommand.split("\\s", 2);
    }
}
