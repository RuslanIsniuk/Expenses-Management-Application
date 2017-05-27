package com.intelliarts.test_app.dao.impl;

import com.intelliarts.test_app.dao.ExpenseDAO;
import com.intelliarts.test_app.dao.ExpenseDateDAO;
import com.intelliarts.test_app.entity.Expense;
import com.intelliarts.test_app.entity.ExpenseDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.intelliarts.test_app.entity.CurrencyType.PLN;
import static com.intelliarts.test_app.entity.CurrencyType.USD;
import static org.junit.Assert.*;


public class HibernateExpenseDAOTest {
    private ExpenseDateDAO expenseDateDAO = new HibernateExpenseDateDAO();
    private ExpenseDAO expenseDAO = new HibernateExpenseDAO();
    private ExpenseDate expenseDate;
    private Expense expenseExpected;
    private int expenseID;
    private int expenseDateID;

    @Before
    public void setUp() {
        expenseDate = new ExpenseDate();
        expenseDate.setDateUsingStr("2017-10-23");
        expenseDateID = expenseDateDAO.insert(expenseDate);
        ExpenseDate expenseDate1 = expenseDateDAO.read(expenseDateID);
        expenseExpected = new Expense();
        expenseExpected.setExpenseAmount(new BigDecimal("25.00"));
        expenseExpected.setExpenseCurrency(USD);
        expenseExpected.setExpenseDescription("Desc 1");
        expenseExpected.setExpenseDate(expenseDate);
        expenseID = expenseDAO.insert(expenseExpected);
    }

    @After
    public void finish() {
        expenseDAO.delete(expenseID);
        expenseDateDAO.delete(expenseDateID);
    }

    @Test
    public void insert() {
        Expense expenseActual = expenseDAO.read(expenseID);
        assertEquals(expenseExpected.getExpenseAmount(), expenseActual.getExpenseAmount());
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

//    @Test
//    public void deleteUsingDate() {
//        Expense expenseExpected2 = new Expense();
//        expenseExpected2.setExpenseDateUsingStr("2017-05-20");
//        expenseExpected2.setExpenseAmount(new BigDecimal("100.00"));
//        expenseExpected2.setExpenseCurrency(PLN);
//        expenseExpected2.setExpenseDescription("test2 Description");
//        expenseDAO.insert(expenseExpected2);
//        expenseDAO.deleteUsingDate(expenseExpected.getExpenseDate());
//
//        List<Expense> expenseList = expenseDAO.readAll();
//
//        assertEquals(0, expenseList.size());
//    }

    @Test
    public void read(){
        Expense expenseActual = expenseDAO.read(expenseID);

        assertEquals(expenseExpected,expenseActual);
    }

    @Test
    public void readAll(){
        Expense expenseExpected2 = new Expense();
        expenseExpected2.setExpenseDate(expenseDate);
        expenseExpected2.setExpenseAmount(new BigDecimal("100.00"));
        expenseExpected2.setExpenseCurrency(PLN);
        expenseExpected2.setExpenseDescription("test2 Description");
        expenseDAO.insert(expenseExpected2);

        List<Expense> expenseList = expenseDAO.readAll();
        expenseDAO.delete(expenseExpected2.getExpenseID());

        assertEquals(2,expenseList.size());
    }
}