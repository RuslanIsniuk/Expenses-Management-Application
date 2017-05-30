package com.expenditures.controller.command.impl;

import com.expenditures.controller.command.Command;
import com.expenditures.controller.exceptions.IncorrectCommandInputException;
import com.expenditures.model.services.TotalExpenses;
import org.apache.log4j.Logger;

public class TotalCommand extends Command {
    private static final Logger logger = Logger.getLogger(TotalCommand.class);
    private static final String COMMAND_PARSER = "[t][o][t][a][l]\\s[A-Z]{3}";
    private static final String ERROR_MESSAGE = "Command not found!\nPerhaps you wanted to say \"total CUR\" ?";

    private TotalExpenses totalExpenses = new TotalExpenses();

    @Override
    public void execute(String enteredCommand) {
        String[] userInput;

        try {
            if (!checkCommand(enteredCommand, COMMAND_PARSER)) {
                throw new IncorrectCommandInputException(ERROR_MESSAGE);
            }

            userInput = getDataStrFromUserInput(enteredCommand);

            if (isCurrencyTypeIncorrect(userInput[1])) {
                throw new IncorrectCommandInputException(ERROR_MESSAGE);
            }

            totalExpenses.execute(userInput[1]);
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
