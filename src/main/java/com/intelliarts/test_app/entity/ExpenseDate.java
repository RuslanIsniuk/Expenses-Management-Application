package com.intelliarts.test_app.entity;

import javax.persistence.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "dates")
public class ExpenseDate {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "dates_id",unique = true,nullable = false)
    private int dateID;
    @Column(name = "expenses_date",unique = true,nullable = false)
    private Date date;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "expenseDate")
    private Set<Expense> expenseSet = null;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpenseDate that = (ExpenseDate) o;

        if (dateID != that.dateID) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return expenseSet != null ? expenseSet.equals(that.expenseSet) : that.expenseSet == null;
    }

    @Override
    public int hashCode() {
        int result = dateID;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (expenseSet != null ? expenseSet.hashCode() : 0);
        return result;
    }

    public int getDateID() {
        return dateID;
    }

    public void setDateID(int dateID) {
        this.dateID = dateID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}
