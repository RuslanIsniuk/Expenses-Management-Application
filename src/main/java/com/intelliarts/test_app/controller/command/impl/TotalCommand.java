package com.intelliarts.test_app.controller.command.impl;

import com.intelliarts.test_app.controller.command.Command;
import com.intelliarts.test_app.controller.exceptions.IncorrectCommandInputException;
import org.apache.log4j.Logger;

public class TotalCommand extends Command{
    private static final Logger logger = Logger.getLogger(TotalCommand.class);
    private final static String COMMAND_PARSER = "[t][o][t][a][l]\\s[A-Z]{3}";

    @Override
    public void execute(String enteredCommand) {
        try {
            if (!checkCommand(enteredCommand,COMMAND_PARSER)) {
                throw new IncorrectCommandInputException("Command not found!\nPerhaps you wanted to say \"total CUR\" ?");
            }
            //add service method
        } catch (IncorrectCommandInputException ie) {
            logger.info(ie);
        }
    }
}
