package com.expenditures.controller.command.impl;

import com.expenditures.controller.command.Command;
import com.expenditures.controller.exceptions.IncorrectCommandInputException;
import com.expenditures.model.services.GetListSortedByDate;
import org.apache.log4j.Logger;

public class ListCommand extends Command {
    private static final Logger logger = Logger.getLogger(ListCommand.class);
    private static final String COMMAND_PARSER = "[l][i][s][t]";
    private static final String ERROR_MESSAGE = "Command not found!\nPerhaps you wanted to say \"list\" ?";

    private GetListSortedByDate getListSortedByDate = new GetListSortedByDate();

    public ListCommand() {
    }

    public ListCommand(GetListSortedByDate getListSortedByDate) {
        this.getListSortedByDate = getListSortedByDate;
    }

    @Override
    public void execute(String enteredCommand) {
        try {
            if (!checkCommand(enteredCommand, COMMAND_PARSER)) {
                throw new IncorrectCommandInputException(ERROR_MESSAGE);
            }

            getListSortedByDate.execute();
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
