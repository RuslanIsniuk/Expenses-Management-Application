package com.intelliarts.test_app.dao.impl;

import com.intelliarts.test_app.dao.DateDAO;
import com.intelliarts.test_app.entity.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class HibernateDateDAOTest {
    private DateDAO dateDAO = new HibernateDateDAO();


    //    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
//    private EntityManager em = emf.createEntityManager();
//    private DateDAO dateDAO = new DateRepository(em);
    private Date dateExpected = new Date();
    private int dateID;

    @Before
    public void setUp() {
        dateExpected = new Date();
        dateExpected.setDateUsingStr("2017-05-27");
        dateExpected.setExpenseSet(new HashSet<>());

        dateID = dateDAO.insert(dateExpected);
    }

    @After
    public void finish() {
        dateDAO.delete(dateExpected.getDateID());
    }

    @Test
    public void insert() {
        Date dateActual = dateDAO.read(dateID);
        assertEquals(dateExpected, dateActual);
    }

    @Test
    public void delete() {
        dateDAO.delete(dateID);
        Date dateActual = dateDAO.read(dateID);

        assertEquals(false, dateActual instanceof Date);
    }

    @Test
    public void update() {
        dateExpected.setDateUsingStr("2015-05-16");
        dateDAO.update(dateExpected);

        Date dateActual = dateDAO.read(dateID);

        assertEquals(dateExpected, dateActual);
    }

    @Test
    public void deleteUsingDate() {
        Date dateExpected2 = new Date();
        dateExpected2.setDateUsingStr("2017-02-20");
        dateExpected2.setExpenseSet(new HashSet<>());

        dateDAO.insert(dateExpected2);
        dateDAO.delete(dateExpected2.getDateID());
        Date dateActual2 = dateDAO.read(dateExpected2.getDateID());

        assertEquals(null, dateActual2);
    }

    @Test
    public void read() {
        Date dateActual = dateDAO.read(dateID);

        assertEquals(dateExpected, dateActual);
    }

    @Test
    public void readUsingDate() {
        Date dateActual = dateDAO.readUsingDate(dateExpected.getDate());

        assertEquals(dateExpected, dateActual);
    }

    @Test
    public void readNotExist() {
        Date dateNotExist = new Date();
        dateNotExist.setDateUsingStr("2014-06-28");
        Date dateActual = dateDAO.readUsingDate(dateNotExist.getDate());

        assertEquals(null, dateActual);
    }

    @Test
    public void readAll() {
        Date dateExpected2 = new Date();
        dateExpected2.setDateUsingStr("2017-05-20");
        dateExpected2.setExpenseSet(new HashSet<>());
        dateDAO.insert(dateExpected2);

        List<Date> dateList = dateDAO.readAll();
        dateDAO.delete(dateExpected2.getDateID());

        assertEquals(2, dateList.size());
    }
}