package com.intelliarts.test_app.dao.impl;

import com.intelliarts.test_app.DBdata.ConnectionUtil;
import com.intelliarts.test_app.dao.ExpenseDateDAO;
import com.intelliarts.test_app.entity.ExpenseDate;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class HibernateExpenseDateDAO implements ExpenseDateDAO{
    private static final Logger logger = Logger.getLogger(HibernateExpenseDateDAO.class);
    private SessionFactory factory = ConnectionUtil.getSessionFactory();

    @Override
    public ExpenseDate read(int dateID){
        ExpenseDate expenseDate = null;
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.getTransaction();
            transaction.begin();
            expenseDate = session.get(ExpenseDate.class, Integer.valueOf(dateID));
            session.getTransaction().commit();
        } catch (SQLGrammarException sge) {
            logger.error(sge);
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        } finally {
            session.close();
        }
        return expenseDate;
    }

    @Override
    public List<ExpenseDate> readAll(){
        List<ExpenseDate> expenseDateList = new ArrayList<>();
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.getTransaction();
            transaction.begin();
            String sqlQuery = "from " + ExpenseDate.class.getName();
            Query<ExpenseDate> query = (Query<ExpenseDate>) session.createQuery(sqlQuery);
            expenseDateList = query.getResultList();
        } catch (SQLGrammarException sge) {
            logger.error(sge);
        } catch (RuntimeException e) {
            logger.error(e);
        } finally {
            session.close();
        }
        return expenseDateList;
    }

    public int insert(ExpenseDate expenseDate){
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(expenseDate);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        } finally {
            session.close();
        }
        return expenseDate.getDateID();
    }

    @Override
    public void update(ExpenseDate expenseDate){
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.beginTransaction();
            session.update(expenseDate);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int dateID){
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.beginTransaction();
            ExpenseDate client = session.get(ExpenseDate.class, Integer.valueOf(dateID));
            session.delete(client);
            session.getTransaction().commit();
        } catch (SQLGrammarException sge) {
            logger.error(sge);
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        } finally {
            session.close();
        }
    }

    public void deleteUsingDate(Date expenseDate){
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.beginTransaction();
            String sqlQuery = "DELETE FROM " + ExpenseDate.class.getName() + " ed WHERE ed.date = :Date";
            Query<ExpenseDate> query = (Query<ExpenseDate>) session.createQuery(sqlQuery);
            query.setParameter("Date", expenseDate);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (SQLGrammarException sge) {
            logger.error(sge);
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        } finally {
            session.close();
        }
    }
}
