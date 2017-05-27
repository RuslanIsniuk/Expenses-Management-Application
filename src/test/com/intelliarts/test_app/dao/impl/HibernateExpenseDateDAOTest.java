package com.intelliarts.test_app.dao.impl;

import com.intelliarts.test_app.dao.ExpenseDateDAO;
import com.intelliarts.test_app.entity.ExpenseDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class HibernateExpenseDateDAOTest {
    private ExpenseDate expenseDateExpected = new ExpenseDate();
    private ExpenseDateDAO expenseDateDAO = new HibernateExpenseDateDAO();
    private int dateID;

    @Before
    public void setUp() {
        expenseDateExpected = new ExpenseDate();
        expenseDateExpected.setDateUsingStr("2017-05-27");
        expenseDateExpected.setExpenseSet(new HashSet<>());

        dateID = expenseDateDAO.insert(expenseDateExpected);
    }

    @After
    public void finish() {
        expenseDateDAO.delete(expenseDateExpected.getDateID());
    }

    @Test
    public void insert() {
        ExpenseDate expenseDateActual = expenseDateDAO.read(dateID);

        assertEquals(expenseDateExpected, expenseDateActual);
    }

    @Test
    public void delete() {
        expenseDateDAO.delete(dateID);
        ExpenseDate expenseDateActual = expenseDateDAO.read(dateID);

        assertEquals(false, expenseDateActual instanceof ExpenseDate);
    }

    @Test
    public void update() {
        expenseDateExpected.setDateUsingStr("2015-05-16");
        expenseDateDAO.update(expenseDateExpected);

        ExpenseDate expenseDateActual = expenseDateDAO.read(dateID);

        assertEquals(expenseDateExpected, expenseDateActual);
    }

//    @Test
//    public void deleteUsingDate() {
//        ExpenseDate ExpenseDateExpected2 = new ExpenseDate();
//        ExpenseDateExpected2.setExpenseDateDateUsingStr("2017-05-20");
//        ExpenseDateExpected2.setExpenseDateAmount(new BigDecimal("100.00"));
//        ExpenseDateExpected2.setExpenseDateCurrency(PLN);
//        ExpenseDateExpected2.setExpenseDateDescription("test2 Description");
//        ExpenseDateDAO.insert(ExpenseDateExpected2);
//        ExpenseDateDAO.deleteUsingDate(ExpenseDateExpected.getExpenseDateDate());
//
//        List<ExpenseDate> ExpenseDateList = ExpenseDateDAO.readAll();
//
//        assertEquals(0, ExpenseDateList.size());
//    }

    @Test
    public void read(){
        ExpenseDate expenseDateActual = expenseDateDAO.read(dateID);

        assertEquals(expenseDateExpected,expenseDateActual);
    }

    @Test
    public void readAll(){
        ExpenseDate expenseDateExpected2 = new ExpenseDate();
        expenseDateExpected2.setDateUsingStr("2017-05-20");
        expenseDateExpected2.setExpenseSet(new HashSet<>());
        expenseDateDAO.insert(expenseDateExpected2);

        List<ExpenseDate> expenseDateList = expenseDateDAO.readAll();
        expenseDateDAO.delete(expenseDateExpected2.getDateID());

        assertEquals(2,expenseDateList.size());
    }
}