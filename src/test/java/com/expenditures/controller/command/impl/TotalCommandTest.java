package com.expenditures.controller.command.impl;

import com.expenditures.model.services.TotalExpenses;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TotalCommandTest {
    private static final String DEFAULT_COMMAND = "total USD";
    private static final String WRONG_COMMAND = "totalid USD";
    private static final String WRONG_CURRENCY_COMMAND = "total BBB";
    private TotalCommand totalCommand;

    @Mock
    TotalExpenses totalExpenses;

    @Before
    public void setUp() throws Exception {
        totalCommand = new TotalCommand(totalExpenses);
    }

    @Test
    public void executeDefault() {
        totalCommand.execute(DEFAULT_COMMAND);
        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(totalExpenses).execute(argument.capture());
    }

    @Test
    public void executeThrowIncorrectCommandInputException() {
        totalCommand.execute(WRONG_COMMAND);
        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(totalExpenses, never()).execute(argument.capture());
    }

    @Test
    public void executeWithWrongCurrencyType() {
        totalCommand.execute(WRONG_CURRENCY_COMMAND);
        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(totalExpenses, never()).execute(argument.capture());
    }
}