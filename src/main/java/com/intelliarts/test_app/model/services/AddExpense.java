package com.intelliarts.test_app.model.services;

import com.intelliarts.test_app.dao.DateDAO;
import com.intelliarts.test_app.dao.impl.HibernateDateDAO;
import com.intelliarts.test_app.entity.Date;
import com.intelliarts.test_app.entity.Expense;

public class AddExpense {
    private DateDAO dateDAO = new HibernateDateDAO();
    private Date date;

    public AddExpense(){
    }

    public AddExpense(DateDAO dateDAO, Date date) {
        this.dateDAO = dateDAO;
        this.date = date;
    }

    public void execute(Date dateFromUser) {
        if (isDateAlreadyExistInDataBase(dateFromUser.getDate())) {
            Expense expenseFromInput = dateFromUser.getExpenseSet().iterator().next();
            expenseFromInput.setDate(date);
            date.getExpenseSet().add(expenseFromInput);
            dateDAO.update(date);
            System.out.println("\n" + date.toString());
        } else {
            dateDAO.insert(dateFromUser);
            System.out.println("\n" + dateFromUser.toString());
        }
    }

    private boolean isDateAlreadyExistInDataBase(java.sql.Date dateField) {
        date = dateDAO.readUsingDate(dateField);

        if (date == null) {
            return false;
        } else {
            return true;
        }
    }
}
