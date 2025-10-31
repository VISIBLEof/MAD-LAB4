package com.example.expensetracker.model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseData {

    private static final List<Expense> expenses = new ArrayList<>();

    static {
        expenses.add(new Expense(1, 10000, "KHR", "Food", "Lunch", "2025-10-10"));
        expenses.add(new Expense(2, 5, "USD", "Transport", "Taxi", "2025-10-11"));
    }

    public static List<Expense> getExpenses() {
        return expenses;
    }

    public static void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public static Expense getExpenseById(long id) {
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                return expense;
            }
        }
        return null;
    }
}