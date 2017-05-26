package com.intelliarts.test_app.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "expenses_id", unique = true, nullable = false)
    private int expenseID;
    @Column(name = "expenses_date",nullable = false)
    private Date expenseDate;
    @Column(name = "expenses_amount", nullable = false)
    private BigDecimal expenseAmount;
    private CurrencyType expenseCurrency;
    private String expenseDescription;

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public BigDecimal getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(BigDecimal expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public CurrencyType getExpenseCurrency() {
        return expenseCurrency;
    }

    public void setExpenseCurrency(CurrencyType expenseCurrency) {
        this.expenseCurrency = expenseCurrency;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }
}
