package com.expenditures.dao.impl;

import com.expenditures.dbdata.ConnectionUtil;
import com.expenditures.entity.Date;
import com.expenditures.dao.DateDAO;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HibernateDateDAO implements DateDAO {
    private static final Logger logger = Logger.getLogger(HibernateDateDAO.class);
    private SessionFactory factory = ConnectionUtil.getSessionFactory();

    @Override
    public Date read(int dateID){
        Date date = null;
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.getTransaction();
            transaction.begin();
            date = session.get(Date.class, Integer.valueOf(dateID));
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
        return date;
    }

    @Override
    public Date readUsingDate(java.sql.Date dateVar) {
        Date date = null;
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.getTransaction();
            transaction.begin();
            String sqlQuery = "FROM " + Date.class.getName() + " ed WHERE ed.date = :Date";
            Query<Date> query = (Query<Date>) session.createQuery(sqlQuery);
            query.setParameter("Date", dateVar);
            date = query.getSingleResult();
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
        return date;
    }

    @Override
    public List<Date> readAll(){
        List<Date> dateList = new ArrayList<>();
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.getTransaction();
            transaction.begin();
            String sqlQuery = "FROM " + Date.class.getName();
            Query<Date> query = (Query<Date>) session.createQuery(sqlQuery);
            dateList = query.getResultList();
        } catch (SQLGrammarException sge) {
            logger.error(sge);
        } catch (RuntimeException e) {
            logger.error(e);
        } finally {
            session.close();
        }
        return dateList;
    }

    public int insert(Date date){
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(date);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        } finally {
            session.close();
        }
        return date.getDateID();
    }

    @Override
    public void update(Date date){
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.beginTransaction();
            session.update(date);
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
            Date date = session.get(Date.class, Integer.valueOf(dateID));
            session.delete(date);
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
    public void deleteUsingDate(java.sql.Date date){
        Transaction transaction = null;
        Session session = factory.getCurrentSession();

        try {
            transaction = session.beginTransaction();
            String sqlQuery = "DELETE FROM " + Date.class.getName() + " ed WHERE ed.date = :Date";
            Query<Date> query = (Query<Date>) session.createQuery(sqlQuery);
            query.setParameter("Date", date);
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
