package com.expenditures.model.services;

import com.expenditures.dao.DateDAO;
import com.expenditures.entity.CurrencyType;
import com.expenditures.entity.Date;
import com.expenditures.entity.Expense;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TotalExpensesTest {
    private static final CurrencyType currencyType = CurrencyType.EUR;
    private TotalExpenses totalExpenses;

    @Mock
    DateDAO dateDAO;

    List<Date> dateList;
    Set<Expense> expenseSet;

    @Before
    public void setUp() throws Exception {
        totalExpenses = new TotalExpenses(dateDAO);

        Date dateFromDB1 = new Date();
        dateFromDB1.setDateUsingStr("2016-03-15");

        Expense expense = new Expense();
        expense.setExpenseDescription("Description0");
        expense.setExpenseAmount(new BigDecimal("10.00"));
        expense.setExpenseCurrency(currencyType);
        expense.setDate(dateFromDB1);

        Expense expense1 = new Expense();
        expense1.setExpenseDescription("Description1");
        expense1.setExpenseAmount(new BigDecimal("10.00"));
        expense1.setExpenseCurrency(currencyType);
        expense1.setDate(dateFromDB1);

        expenseSet = new HashSet<>();
        expenseSet.add(expense);
        expenseSet.add(expense1);

        dateFromDB1.setExpenseSet(expenseSet);
        dateList = new ArrayList<>();
        dateList.add(dateFromDB1);

    }

    @Test
    public void executeDefault(){
        when(dateDAO.readAll()).thenReturn(dateList);
        totalExpenses.execute(currencyType.getAbbreviation());

        assertEquals(new BigDecimal("20.00"),totalExpenses.getTotalAmount());
    }

    @Test
    public void executeWithNoDatesFoundException(){
        when(dateDAO.readAll()).thenReturn(new ArrayList<>());
        totalExpenses.execute(currencyType.getAbbreviation());

        assertEquals(new BigDecimal("0.00"),totalExpenses.getTotalAmount());
    }

    @Test
    public void executeWithDifferentCurrencyType(){
        Expense expense = new Expense();
        expense.setExpenseDescription("Description");
        expense.setExpenseAmount(new BigDecimal("10.00"));
        expense.setExpenseCurrency(CurrencyType.AUD);
        expense.setDate(dateList.get(0));
        dateList.get(0).getExpenseSet().add(expense);
        when(dateDAO.readAll()).thenReturn(dateList);
        totalExpenses.execute(currencyType.getAbbreviation());

        assertNotEquals(new BigDecimal("0.00"),totalExpenses.getTotalAmount());
        assertNotEquals(new BigDecimal("20.00"),totalExpenses.getTotalAmount());
    }
}