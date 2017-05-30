package com.expenditures.controller.command;

import com.expenditures.entity.CurrencyType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {
    public abstract void execute(String enteredCommand);

    public abstract String[] getDataStrFromUserInput(String enteredCommand);

    protected boolean checkCommand(String enteredCommand, String commandParser) {
        Pattern pattern = Pattern.compile(commandParser);
        Matcher matcher = pattern.matcher(enteredCommand);
        return matcher.matches();
    }

    protected boolean isCurrencyTypeIncorrect(String currencyStr) {
        boolean typeIncorrect = true;

        for (CurrencyType ct : CurrencyType.values()) {
            if (ct.getAbbreviation().equals(currencyStr)) {
                typeIncorrect = false;
            }
        }
        return typeIncorrect;
    }
}
