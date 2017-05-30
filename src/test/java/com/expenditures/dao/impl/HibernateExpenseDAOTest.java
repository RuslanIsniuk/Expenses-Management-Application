package com.expenditures.dao.impl;

import com.expenditures.dao.ExpenseDAO;
import com.expenditures.entity.CurrencyType;
import com.expenditures.entity.Date;
import com.expenditures.entity.Expense;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;


public class HibernateExpenseDAOTest {
    //    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
//    private EntityManager em = emf.createEntityManager();
//    private DateDAO DateDAO = new DateRepository(em);
//    private ExpenseDAO expenseDAO = new ExpenseRepository(em);
//
    private com.expenditures.dao.DateDAO DateDAO = new HibernateDateDAO();
    private ExpenseDAO expenseDAO = new HibernateExpenseDAO();


    private Date date;
    private Expense expenseExpected;
    private int expenseID;
    private int DateID;

    @Before
    public void setUp() {
        date = new Date();
        expenseExpected = new Expense();

        date.setDateUsingStr("2017-05-01");
        DateID = DateDAO.insert(date);

        expenseExpected.setExpenseAmount(new BigDecimal("25.00"));
        expenseExpected.setExpenseCurrency(CurrencyType.USD);
        expenseExpected.setExpenseDescription("Desc 1");
        expenseExpected.setDate(date);
        expenseID = expenseDAO.insert(expenseExpected);

    }

    @After
    public void finish() {
        expenseDAO.delete(expenseID);
        DateDAO.delete(DateID);
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

    @Test
    public void read() {
        Expense expenseActual = expenseDAO.read(expenseID);

        assertEquals(expenseExpected, expenseActual);
    }

    @Test
    public void readAll() {
        Expense expenseExpected2 = new Expense();
        expenseExpected2.setDate(date);
        expenseExpected2.setExpenseAmount(new BigDecimal("100.00"));
        expenseExpected2.setExpenseCurrency(CurrencyType.PLN);
        expenseExpected2.setExpenseDescription("test2 Description");
        expenseDAO.insert(expenseExpected2);

        List<Expense> expenseList = expenseDAO.readAll();
        expenseDAO.delete(expenseExpected2.getExpenseID());

        assertEquals(2, expenseList.size());
    }
}