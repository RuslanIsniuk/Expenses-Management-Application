package com.intelliarts.test_app.controller.command.impl;

import com.intelliarts.test_app.controller.command.Command;
import com.intelliarts.test_app.controller.exceptions.IncorrectCommandInputException;
import org.apache.log4j.Logger;

public class AddCommand extends Command {
    private static final Logger logger = Logger.getLogger(AddCommand.class);
    private static final String COMMAND_PARSER = "[a][d][d]\\s[12]\\d{3}[-](([0][1-9])||([1][0-2]))[-](([0][1-9])||([12][0-9])||([3][01]))\\s[A-Z]{3}\\s.{3,100}";
    private static final String ERROR_MESSAGE = "Command not found!\nPerhaps you wanted to say \"add yyyy-mm-dd CUR description\" ?";
    private String[] userInput;

    @Override
    public void execute(String enteredCommand) {
        try {
            if (!checkCommand(enteredCommand, COMMAND_PARSER)) {
                throw new IncorrectCommandInputException(ERROR_MESSAGE);
            }

            userInput = getDataStrFromUserInput(enteredCommand);

            if (isCurrencyTypeIncorrect(userInput[3])) {
                throw new IncorrectCommandInputException(ERROR_MESSAGE);
            }
            //add service method
        } catch (IncorrectCommandInputException ie) {
            logger.info(ie);
        }
    }

    @Override
    public String[] getDataStrFromUserInput(String enteredCommand) {
        return enteredCommand.split("\\s", 5);
    }
}
