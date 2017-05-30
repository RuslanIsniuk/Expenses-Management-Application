package com.expenditures.controller.command.impl;

import com.expenditures.model.services.GetListSortedByDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListCommandTest {
    private static final String DEFAULT_COMMAND = "list";
    private static final String WRONG_COMMAND = "lister";
    private ListCommand listCommand;

    @Mock
    GetListSortedByDate getListSortedByDate;

    @Before
    public void setUp() throws Exception {
        listCommand = new ListCommand(getListSortedByDate);
    }

    @Test
    public void executeDefault() {
        listCommand.execute(DEFAULT_COMMAND);
        verify(getListSortedByDate).execute();
    }

    @Test
    public void executeThrowIncorrectCommandInputException() {
        listCommand.execute(WRONG_COMMAND);
        verify(getListSortedByDate, never()).execute();
    }

}