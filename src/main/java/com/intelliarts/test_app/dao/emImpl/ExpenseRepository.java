package com.intelliarts.test_app.dao.emImpl;

import com.intelliarts.test_app.dao.ExpenseDAO;
import com.intelliarts.test_app.entity.Expense;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.List;

public class ExpenseRepository implements ExpenseDAO {
    private EntityManager em;

    public ExpenseRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Expense read(int expenseID) {
        return em.find(Expense.class, expenseID);
    }

    @Override
    public List<Expense> readAll() {
        return null;
    }


    @Override
    public int insert(Expense expense) {
        EntityTransaction tr = em.getTransaction();
        if (!tr.isActive())
            tr.begin();
        em.persist(expense);
        em.flush();
        tr.commit();
        em.clear();
        return expense.getExpenseID();
    }

    @Override
    public void update(Expense expense) {
        em.merge(expense);
    }

    @Override
    public void delete(int expenseID) {
        Expense expense = read(expenseID);
        if (expense != null) {
            EntityTransaction tr = em.getTransaction();
            if (!tr.isActive())
                tr.begin();
            em.remove(expense);
            em.flush();
            tr.commit();
            em.clear();
        }
    }

}
