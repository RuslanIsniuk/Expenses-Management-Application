package com.intelliarts.test_app.dao.impl;

import com.intelliarts.test_app.dao.ExpenseDAO;
import com.intelliarts.test_app.entity.Expense;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static com.intelliarts.test_app.entity.CurrencyType.PLN;
import static com.intelliarts.test_app.entity.CurrencyType.USD;
import static org.junit.Assert.*;


public class HibernateExpenseDAOTest {
    private ExpenseDAO expenseDAO = new HibernateExpenseDAO();
    private Expense expenseExpected;
    private int expenseID;

    @Before
    public void setUp() {
        expenseExpected = new Expense();
        expenseExpected.setExpenseDateUsingStr("2017-05-20");
        expenseExpected.setExpenseAmount(new BigDecimal("25.00"));
        expenseExpected.setExpenseCurrency(USD);
        expenseExpected.setExpenseDescription("test Description");

        expenseID = expenseDAO.insert(expenseExpected);
    }

    @After
    public void finish() {
        expenseDAO.delete(expenseExpected.getExpenseID());
    }

    @Test
    public void insert() {
        Expense expenseActual = expenseDAO.read(expenseID);

        assertEquals(expenseExpected, expenseActual);
    }

    @Test
    public void delete() {
        expenseDAO.delete(expenseID);
        Expense expenseActual = expenseDAO.read(expenseID);

        assertEquals(false, expenseActual instanceof Expense);
    }

    @Test
    public void update() {
        expenseExpected.setExpenseAmount(new BigDecimal("50.00"));
        expenseExpected.setExpenseDescription("test2 Description");
        expenseDAO.update(expenseExpected);

        Expense expenseActual = expenseDAO.read(expenseID);

        assertEquals(expenseExpected, expenseActual);
    }

    @Test
    public void deleteUsingDate() {
        Expense expenseExpected2 = new Expense();
        expenseExpected2.setExpenseDateUsingStr("2017-05-20");
        expenseExpected2.setExpenseAmount(new BigDecimal("100.00"));
        expenseExpected2.setExpenseCurrency(PLN);
        expenseExpected2.setExpenseDescription("test2 Description");
        expenseDAO.insert(expenseExpected2);
        expenseDAO.deleteUsingDate(expenseExpected.getExpenseDate());

        List<Expense> expenseList = expenseDAO.readAll();

        assertEquals(0, expenseList.size());
    }
}