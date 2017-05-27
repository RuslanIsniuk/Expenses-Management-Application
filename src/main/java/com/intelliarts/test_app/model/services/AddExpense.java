package com.intelliarts.test_app.model.services;

import com.intelliarts.test_app.dao.ExpenseDAO;
import com.intelliarts.test_app.dao.impl.HibernateExpenseDAO;
import com.intelliarts.test_app.entity.Expense;

public class AddExpense {
    private ExpenseDAO expenseDAO = new HibernateExpenseDAO();

    public void insertNewExpense(Expense newExpense){
        expenseDAO.insert(newExpense);
        System.out.println("Entry has been added successfully.");
    }
}
