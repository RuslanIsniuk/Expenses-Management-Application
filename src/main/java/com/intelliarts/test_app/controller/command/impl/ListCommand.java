package com.intelliarts.test_app.controller.command.impl;

import com.intelliarts.test_app.controller.command.Command;
import com.intelliarts.test_app.controller.exceptions.IncorrectCommandInputException;
import org.apache.log4j.Logger;

public class ListCommand extends Command{
    private static final Logger logger = Logger.getLogger(ListCommand.class);
    private final static String COMMAND_PARSER = "[l][i][s][t]";

    @Override
    public void execute(String enteredCommand) {
        try {
            if (!checkCommand(enteredCommand,COMMAND_PARSER)) {
                throw new IncorrectCommandInputException("Command not found!\nPerhaps you wanted to say \"list\" ?");
            }
            //add service method
        } catch (IncorrectCommandInputException ie) {
            logger.info(ie);
        }
    }
}
