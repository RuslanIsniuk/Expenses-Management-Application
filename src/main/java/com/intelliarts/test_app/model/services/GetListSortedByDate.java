package com.intelliarts.test_app.model.services;

import com.intelliarts.test_app.dao.ExpenseDAO;
import com.intelliarts.test_app.dao.impl.HibernateExpenseDAO;
import com.intelliarts.test_app.entity.Expense;

import java.util.ArrayList;
import java.util.List;

public class GetListSortedByDate {
    private ExpenseDAO expenseDAO = new HibernateExpenseDAO();
//    private DateComparator dateComparator = new DateComparator();
//
//    public void getList(){
//        List<Expense> expenseList = expenseDAO.readAll();
//        expenseList.sort(dateComparator);
//
//        for (Expense expenseFromList:expenseList) {
//            System.out.println(expenseFromList.getExpenseDate()+"\n"+expenseFromList.getExpenseAmount());
//        }
//    }
}
