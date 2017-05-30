package com.expenditures.dao;

import com.expenditures.entity.Expense;

import java.util.List;

public interface ExpenseDAO {
    Expense read(int expenseID);

    List<Expense> readAll();

    int insert(Expense expense);

    void update(Expense expense);

    void delete(int expenseID);
}
