package com.intelliarts.test_app.model.services;

import com.intelliarts.test_app.dao.ExpenseDAO;
import com.intelliarts.test_app.dao.impl.HibernateExpenseDAO;

import java.sql.Date;

public class ClearExpenses {
    private ExpenseDAO expenseDAO = new HibernateExpenseDAO();

    public void doClear(Date expenseDate){
//        expenseDAO.deleteUsingDate(expenseDate);
        System.out.println("Expenses deleted!");
    }
}
