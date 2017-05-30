package com.expenditures.controller.command.impl;

import com.expenditures.entity.Date;
import com.expenditures.model.services.AddExpense;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddCommandTest {
    private static final String DEFAULT_COMMAND = "add 2017-04-25 12 USD Jogurt";
    private static final String WRONG_COMMAND = "addw 2017-04-252 12 USD Jogurt";
    private static final String WRONG_CURRENCY_COMMAND = "add 2017-04-25 12 BBB Jogurt";
    private AddCommand addCommand;

    @Mock
    AddExpense addExpense;

    @Before
    public void setUp() throws Exception {
        addCommand = new AddCommand(addExpense);
    }

    @Test
    public void executeDefault(){
        addCommand.execute(DEFAULT_COMMAND);
        verify(addExpense).execute(any(Date.class));
    }

    @Test
    public void executeThrowIncorrectCommandInputException(){
        addCommand.execute(WRONG_COMMAND);
        verify(addExpense,never()).execute(any());
    }

    @Test
    public void executeWithWrongCurrencyType() {
        addCommand.execute(WRONG_CURRENCY_COMMAND);
        verify(addExpense, never()).execute(any());
    }
}