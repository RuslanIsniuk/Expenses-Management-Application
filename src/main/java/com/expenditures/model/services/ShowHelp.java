package com.expenditures.model.services;


public class ShowHelp {
    private static final String helpMessage = "\nList of commands:\n" +
            "- add yyyy-mm-dd xxxx CUR description  ---  used for create new expense (yyyy-mm-dd - expense date; xxxx - value of expense; CUR - currency type)\n" +
            "- list  ---  show the list of all expenses sorted by date\n" +
            "- clear yyyy-mm-dd  ---  clear all expenses from memory with specified date\n" +
            "- total CUR  ---  print total amount of all expenses specified in CUR currency type\n" +
            "- exit  ---  close application";

    public static String getHelpMessage() {
        return helpMessage;
    }
}
