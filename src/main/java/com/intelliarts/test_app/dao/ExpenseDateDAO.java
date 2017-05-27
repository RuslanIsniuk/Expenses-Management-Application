package com.intelliarts.test_app.dao;

import com.intelliarts.test_app.entity.Expense;
import com.intelliarts.test_app.entity.ExpenseDate;

import java.sql.Date;
import java.util.List;

public interface ExpenseDateDAO {
    ExpenseDate read(int dateID);

    List<ExpenseDate> readAll();

    int insert(ExpenseDate expenseDate);

    void update(ExpenseDate expenseDate);

    void delete(int dateID);

    void deleteUsingDate(Date expenseDate);
}
