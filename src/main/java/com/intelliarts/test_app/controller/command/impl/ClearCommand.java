package com.intelliarts.test_app.controller.command.impl;

import com.intelliarts.test_app.controller.command.Command;
import com.intelliarts.test_app.controller.exceptions.IncorrectCommandInputException;
import org.apache.log4j.Logger;

public class ClearCommand extends Command {
    private static final Logger logger = Logger.getLogger(ClearCommand.class);
    private final static String COMMAND_PARSER = "[c][l][e][a][r]\\s[12]\\d{3}[-](([0][1-9])||([1][0-2]))[-](([0][1-9])||([12][0-9])||([3][01]))";

    @Override
    public void execute(String enteredCommand) {
        try {
            if (!checkCommand(enteredCommand, COMMAND_PARSER)) {
                throw new IncorrectCommandInputException("Command not found!\nPerhaps you wanted to say \"clear yyyy-mm-dd\" ?");
            }
            //add service method
        } catch (IncorrectCommandInputException ie) {
            logger.info(ie);
        }
    }
}
