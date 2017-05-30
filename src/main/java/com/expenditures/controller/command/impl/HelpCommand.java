package com.expenditures.controller.command.impl;


import com.expenditures.controller.command.Command;
import com.expenditures.controller.exceptions.IncorrectCommandInputException;
import com.expenditures.model.services.ShowHelp;
import org.apache.log4j.Logger;

public class HelpCommand extends Command {
    private static final Logger logger = Logger.getLogger(HelpCommand.class);
    private static final String COMMAND_PARSER = "help";
    private static final String ERROR_MESSAGE = "Command not found!\nPerhaps you wanted to say \"help\" ?";

    @Override
    public void execute(String enteredCommand) {
        try {
            if (!checkCommand(enteredCommand, COMMAND_PARSER)) {
                throw new IncorrectCommandInputException(ERROR_MESSAGE);
            }

            System.out.println(ShowHelp.getHelpMessage());
        } catch (IncorrectCommandInputException ie) {
            logger.info(ie);
            System.out.println(ERROR_MESSAGE);
        }
    }

    @Override
    public String[] getDataStrFromUserInput(String enteredCommand) {
        return enteredCommand.split("\\s", 0);
    }
}
