package com.expenditures.dao.emImpl;

import com.expenditures.dao.DateDAO;
import com.expenditures.entity.Date;

import javax.persistence.EntityManager;
import java.util.List;

public class DateRepository implements DateDAO {
    private EntityManager em;

    public DateRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Date read(int dateID) {
        return em.find(Date.class, dateID);
    }

    @Override
    public List<Date> readAll() {
        return null;
    }

    @Override
    public int insert(Date date) {
        em.getTransaction().begin();
        em.persist(date);
        em.flush();
        em.getTransaction().commit();
        return date.getDateID();
    }

    @Override
    public void update(Date date) {
        em.merge(date);
    }

    @Override
    public void delete(int dateID) {
        Date date = read(dateID);
        if (date != null) {
            em.getTransaction().begin();
            em.remove(date);
            em.flush();
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteUsingDate(java.sql.Date date) {

    }

    @Override
    public Date readUsingDate(java.sql.Date date) {
        return null;
    }
}
