package com.intelliarts.test_app.dao.impl;

import com.intelliarts.test_app.DBdata.ConnectionUtil;
import com.intelliarts.test_app.dao.ExpenseDAO;
import com.intelliarts.test_app.entity.Expense;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class HibernateExpenseDAO implements ExpenseDAO {
    private static final Logger logger = Logger.getLogger(HibernateExpenseDAO.class);
    private SessionFactory factory = ConnectionUtil.getSessionFactory();

    @Override
    public Expense read(int expenseID) {
        Expense expense = null;
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.getTransaction();
            transaction.begin();
            expense = session.get(Expense.class, Integer.valueOf(expenseID));
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
        return expense;
    }

    @Override
    public List<Expense> readAll() {
        List<Expense> expenseList = new ArrayList<>();
        Transaction transaction;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.getTransaction();
            transaction.begin();
            String sqlQuery = "from " + Expense.class.getName();
            Query<Expense> query = (Query<Expense>) session.createQuery(sqlQuery);
            expenseList = query.getResultList();
        } catch (SQLGrammarException sge) {
            logger.error(sge);
        } catch (RuntimeException e) {
            logger.error(e);
        } finally {
            session.close();
        }
        return expenseList;
    }

    @Override
    public int insert(Expense expense) {
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(expense);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        } finally {
            session.close();
        }
        return expense.getExpenseID();
    }

    @Override
    public void update(Expense expense) {
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.beginTransaction();
            session.update(expense);
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
    public void delete(int expenseID) {
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.beginTransaction();
            Expense expense = session.get(Expense.class, Integer.valueOf(expenseID));
            session.delete(expense);
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

    @Override
    public void deleteUsingDate(Date expenseDate) {
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.beginTransaction();
            String sqlQuery = "DELETE FROM " + Expense.class.getName() + " e WHERE e.expenseDate = :Date";
            Query<Expense> query = (Query<Expense>) session.createQuery(sqlQuery);
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
