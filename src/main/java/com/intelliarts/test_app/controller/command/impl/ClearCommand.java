package com.intelliarts.test_app.controller.command.impl;

import com.intelliarts.test_app.controller.command.Command;
import com.intelliarts.test_app.controller.exceptions.IncorrectCommandInputException;
import com.intelliarts.test_app.model.services.ClearExpenses;
import org.apache.log4j.Logger;

public class ClearCommand extends Command {
    private static final Logger logger = Logger.getLogger(ClearCommand.class);
    private static final String COMMAND_PARSER = "[c][l][e][a][r]\\s[12]\\d{3}[-](([0][1-9])||([1][0-2]))[-](([0][1-9])||([12][0-9])||([3][01]))";
    private static final String ERROR_MESSAGE = "Command not found!\nPerhaps you wanted to say \"clear yyyy-mm-dd\" ?";

    private ClearExpenses clearExpenses = new ClearExpenses();

    @Override
    public void execute(String enteredCommand) {
        String[] userInput = getDataStrFromUserInput(enteredCommand);

        try {
            if (!checkCommand(enteredCommand, COMMAND_PARSER)) {
                throw new IncorrectCommandInputException(ERROR_MESSAGE);
            }

            clearExpenses.execute(userInput[1]);
        } catch (IncorrectCommandInputException ie) {
            logger.info(ie);
            System.out.println(ERROR_MESSAGE);
        }
    }

    @Override
    public String[] getDataStrFromUserInput(String enteredCommand) {
        return enteredCommand.split("\\s", 2);
    }
}
