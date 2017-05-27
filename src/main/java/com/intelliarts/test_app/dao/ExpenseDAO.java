package com.intelliarts.test_app.dao;

import com.intelliarts.test_app.entity.Expense;

import java.sql.Date;
import java.util.List;

public interface ExpenseDAO {
    Expense read(int expenseID);

    List<Expense> readAll();

    int insert(Expense expense);

    void update(Expense expense);

    void delete(int expenseID);

//    void deleteUsingDate(Date expenseDate);
}
