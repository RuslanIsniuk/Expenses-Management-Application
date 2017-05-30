package com.expenditures.model.services;

import com.expenditures.entity.Date;
import com.expenditures.dao.DateDAO;
import com.expenditures.entity.Expense;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Iterator;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddExpenseTest {
    private AddExpense addExpense;

    @Mock
    DateDAO dateDAO;
    @Mock
    Date date;
    @Mock
    Date dateFromUser;
    @Mock
    Set<Expense> expenseSet;
    @Mock
    Iterator<Expense> mockIterator;

    @Before
    public void setUp() {
        addExpense = new AddExpense(dateDAO,date);
        when(dateFromUser.getDate()).thenReturn(new java.sql.Date(0));
        when(dateFromUser.getExpenseSet()).thenReturn(expenseSet);
        when(expenseSet.iterator()).thenReturn(mockIterator);
        when(mockIterator.next()).thenReturn(new Expense());
    }

    @Test
    public void executeDateAlreadyExist(){
        when(dateDAO.readUsingDate(any())).thenReturn(date);
        addExpense.execute(dateFromUser);
        verify(dateDAO).update(any());
    }

    @Test
    public void executeDefault(){
        when(dateDAO.readUsingDate(any())).thenReturn(null);
        addExpense.execute(dateFromUser);
        verify(dateDAO).insert(any());
    }
}