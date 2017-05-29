package com.intelliarts.test_app.entity;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "expenses_id", unique = true, nullable = false)
    private int expenseID;

    @Column(name = "expenses_amount", nullable = false)
    private BigDecimal expenseAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "expenses_currency", nullable = false)
    private CurrencyType expenseCurrency;

    @Column(name = "expenses_description", nullable = false)
    private String expenseDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expenses_date_id")
    private Date date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expense expense = (Expense) o;

        if (expenseID != expense.expenseID) return false;
        if (expenseAmount != null ? !expenseAmount.equals(expense.expenseAmount) : expense.expenseAmount != null)
            return false;
        if (expenseCurrency != expense.expenseCurrency) return false;
        if (expenseDescription != null ? !expenseDescription.equals(expense.expenseDescription) : expense.expenseDescription != null)
            return false;
        return date != null ? date.equals(expense.date) : expense.date == null;
    }

    @Override
    public int hashCode() {
        int result = expenseID;
        result = 31 * result + (expenseAmount != null ? expenseAmount.hashCode() : 0);
        result = 31 * result + (expenseCurrency != null ? expenseCurrency.hashCode() : 0);
        result = 31 * result + (expenseDescription != null ? expenseDescription.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
