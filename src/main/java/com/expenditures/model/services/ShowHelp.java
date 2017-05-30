package com.expenditures.model.services;


public class ShowHelp {
    private static final String helpMessage = "\nList of commands:\n" +
            "- add yyyy-mm-dd xxxx CUR description\t--- used for create new expense (yyyy-mm-dd - expense date; xxxx - value of expense; CUR - currency type)\n" +
            "- list\t\t\t\t\t\t\t\t\t--- show the list of all expenses sorted by date\n" +
            "- clear yyyy-mm-dd\t\t\t\t\t\t--- clear all expenses from memory with specified date\n" +
            "- total CUR\t\t\t\t\t\t\t\t--- print total amount of all expenses specified in CUR currency type\n" +
            "- exit\t\t\t\t\t\t\t\t\t--- close application";

    public static String getHelpMessage() {
        return helpMessage;
    }
}
