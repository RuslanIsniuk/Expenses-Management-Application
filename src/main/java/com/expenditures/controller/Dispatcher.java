package com.expenditures.controller;


import com.expenditures.controller.command.impl.ClearCommand;
import com.expenditures.controller.command.impl.ListCommand;
import com.expenditures.controller.exceptions.CommandNotFoundException;
import com.expenditures.controller.command.impl.AddCommand;
import com.expenditures.controller.command.impl.TotalCommand;
import org.apache.log4j.Logger;

public class Dispatcher {
    private static final Logger logger = Logger.getLogger(Dispatcher.class);
    private static final String ERROR_MESSAGE = "Error!Command not found!";

    private AddCommand addCommand = new AddCommand();
    private ClearCommand clearCommand = new ClearCommand();
    private ListCommand listCommand = new ListCommand();
    private TotalCommand totalCommand = new TotalCommand();

    public void commandIdentification(String enteredCommand) {

        try {
            if (enteredCommand.length() < 3) {
                throw new CommandNotFoundException(ERROR_MESSAGE);
            }

            String commandSubStr = enteredCommand.substring(0, 3);

            switch (commandSubStr) {
                case "add":
                    addCommand.execute(enteredCommand);
                    break;
                case "lis":
                    listCommand.execute(enteredCommand);
                    break;
                case "cle":
                    clearCommand.execute(enteredCommand);
                    break;
                case "tot":
                    totalCommand.execute(enteredCommand);
                    break;
                case "hel":
                    break;
                case "exi":
                    break;
                default:
                    throw new CommandNotFoundException(ERROR_MESSAGE);
            }
        } catch (CommandNotFoundException e) {
            logger.info(e);
            System.out.println(ERROR_MESSAGE);
        }
    }
}
