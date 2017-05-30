package com.expenditures.controller;

import com.expenditures.controller.command.impl.*;
import com.expenditures.entity.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DispatcherTest {
    private static final String ADD_COMMAND = "add";
    private static final String LIS_COMMAND = "lis";
    private static final String CLARE_COMMAND = "cle";
    private static final String TOTAL_COMMAND = "tot";
    private static final String HELP_COMMAND = "hel";
    private static final String WRONG_COMMAND = "";
    private Dispatcher dispatcher;

    @Mock
    AddCommand addCommand;
    @Mock
    ClearCommand clearCommand;
    @Mock
    ListCommand listCommand;
    @Mock
    TotalCommand totalCommand;
    @Mock
    HelpCommand helpCommand;

    @Before
    public void setUp() throws Exception {
        dispatcher = new Dispatcher(addCommand,clearCommand,listCommand,totalCommand,helpCommand);
    }

    @Test
    public void executeAddCommand(){
        dispatcher.commandIdentification(ADD_COMMAND);
        verify(addCommand).execute(any());
    }

    @Test
    public void executeListCommand(){
        dispatcher.commandIdentification(LIS_COMMAND);
        verify(listCommand).execute(any());
    }

    @Test
    public void executeClearCommand(){
        dispatcher.commandIdentification(CLARE_COMMAND);
        verify(clearCommand).execute(any());
    }

    @Test
    public void executeTotalCommand(){
        dispatcher.commandIdentification(TOTAL_COMMAND);
        verify(totalCommand).execute(any());
    }

    @Test
    public void executeHelpCommand(){
        dispatcher.commandIdentification(HELP_COMMAND);
        verify(helpCommand).execute(any());
    }

    @Test
    public void executeWrongCommand(){
        dispatcher.commandIdentification(WRONG_COMMAND);
        verify(addCommand,never()).execute(any());
        verify(listCommand,never()).execute(any());
        verify(clearCommand,never()).execute(any());
        verify(totalCommand,never()).execute(any());
        verify(helpCommand,never()).execute(any());
    }
}