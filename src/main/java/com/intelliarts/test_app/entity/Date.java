package com.intelliarts.test_app.entity;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;

@Entity
@Table(name = "dates")
public class Date {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dates_id", unique = true, nullable = false)
    private int dateID;
    @Column(name = "expenses_date", unique = true, nullable = false)
    private java.sql.Date date;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "date")
    private Set<Expense> expenseSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;

        Date that = (Date) o;

        return getDate() != null ? getDate().equals(that.getDate()) : that.getDate() == null;
    }

    @Override
    public int hashCode() {
        return getDate() != null ? getDate().hashCode() : 0;
    }

    public int getDateID() {
        return dateID;
    }

    public void setDateID(int dateID) {
        this.dateID = dateID;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public void setDateUsingStr(String expenseDateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {

            java.util.Date utilDate = formatter.parse(expenseDateStr);
            java.sql.Date mySQLDate = new java.sql.Date(utilDate.getTime());

            this.date = mySQLDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Set<Expense> getExpenseSet() {
        return expenseSet;
    }

    public void setExpenseSet(Set<Expense> expenseSet) {
        this.expenseSet = expenseSet;
    }

    @Override
    public String toString() {
        String str = "" + date;

        for (Expense expenseFromList : expenseSet) {
            str += "\n" + expenseFromList.getExpenseDescription() + " " + expenseFromList.getExpenseAmount() + " " + expenseFromList.getExpenseCurrency();
        }

        return str;
    }
}
