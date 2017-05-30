package com.expenditures.model.services;

import com.expenditures.dao.DateDAO;
import com.expenditures.entity.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClearExpensesTest {
    private ClearExpenses clearExpenses;

    @Mock
    DateDAO dateDAO;
    @Mock
    Date date;

    @Before
    public void setUp() {
        clearExpenses = new ClearExpenses(dateDAO);
    }

    @Test
    public void executeDefault(){
        when(dateDAO.readUsingDate(any())).thenReturn(date);
        clearExpenses.execute("2000-10-20");
        ArgumentCaptor<java.sql.Date> argument = ArgumentCaptor.forClass(java.sql.Date.class);
        verify(dateDAO).deleteUsingDate(argument.capture());
    }

    @Test
    public void executeWithNoDatesFoundException(){
        when(dateDAO.readUsingDate(any())).thenReturn(null);
        clearExpenses.execute("2000-10-20");
        ArgumentCaptor<java.sql.Date> argument = ArgumentCaptor.forClass(java.sql.Date.class);
        verify(dateDAO, never()).deleteUsingDate(argument.capture());
    }
}