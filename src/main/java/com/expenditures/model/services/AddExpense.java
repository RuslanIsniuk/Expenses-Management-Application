package com.expenditures.model.services;

import com.expenditures.dao.DateDAO;
import com.expenditures.dao.impl.HibernateDateDAO;
import com.expenditures.entity.Date;
import com.expenditures.entity.Expense;

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
        return date!=null;
    }
}
