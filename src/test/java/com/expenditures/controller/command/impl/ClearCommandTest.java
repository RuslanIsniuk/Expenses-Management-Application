package com.expenditures.controller.command.impl;

import com.expenditures.model.services.ClearExpenses;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ClearCommandTest {
    private static final String DEFAULT_COMMAND = "clear 2017-04-25";
    private static final String WRONG_COMMAND = "cleard";
    private ClearCommand clearCommand;

    @Mock
    ClearExpenses clearExpenses;

    @Before
    public void setUp() throws Exception {
        clearCommand = new ClearCommand(clearExpenses);

    }

    @Test
    public void executeDefault() {
        clearCommand.execute(DEFAULT_COMMAND);
        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(clearExpenses).execute(argument.capture());
    }

    @Test
    public void executeThrowIncorrectCommandInputException() {
        clearCommand.execute(WRONG_COMMAND);
        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(clearExpenses, never()).execute(argument.capture());
    }

}