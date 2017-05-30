package com.expenditures.controller;


import com.expenditures.controller.command.impl.*;
import com.expenditures.controller.exceptions.CommandNotFoundException;
import org.apache.log4j.Logger;

public class Dispatcher {
    private static final Logger logger = Logger.getLogger(Dispatcher.class);
    private static final String ERROR_MESSAGE = "Error!Command not found!\n Type \"help\" to get list of commands.";

    private AddCommand addCommand = new AddCommand();
    private ClearCommand clearCommand = new ClearCommand();
    private ListCommand listCommand = new ListCommand();
    private TotalCommand totalCommand = new TotalCommand();
    private HelpCommand helpCommand = new HelpCommand();

    public Dispatcher() {
    }

    public Dispatcher(AddCommand addCommand, ClearCommand clearCommand, ListCommand listCommand, TotalCommand totalCommand, HelpCommand helpCommand) {
        this.addCommand = addCommand;
        this.clearCommand = clearCommand;
        this.listCommand = listCommand;
        this.totalCommand = totalCommand;
        this.helpCommand = helpCommand;
    }

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
                    helpCommand.execute(enteredCommand);
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
