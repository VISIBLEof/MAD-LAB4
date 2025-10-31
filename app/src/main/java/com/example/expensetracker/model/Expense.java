package com.example.expensetracker.model;

public class Expense {
    private long id;
    private double amount;
    private String currency;
    private String category;
    private String remark;
    private String createdDate;

    public Expense(long id, double amount, String currency, String category, String remark, String createdDate) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.category = category;
        this.remark = remark;
        this.createdDate = createdDate;
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCategory() {
        return category;
    }

    public String getRemark() {
        return remark;
    }

    public String getCreatedDate() {
        return createdDate;
    }
}