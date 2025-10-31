package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.ExpenseData;

public class ExpenseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        TextView tvAmount = findViewById(R.id.tvAmount);
        TextView tvCurrency = findViewById(R.id.tvCurrency);
        TextView tvCategory = findViewById(R.id.tvCategory);
        TextView tvRemark = findViewById(R.id.tvRemark);
        TextView tvCreatedDate = findViewById(R.id.tvCreatedDate);
        Button btnAddNewExpense = findViewById(R.id.btnAddNewExpense);
        Button btnBackToHome = findViewById(R.id.btnBackToHome);

        long expenseId = getIntent().getLongExtra("expenseId", -1);

        if (expenseId != -1) {
            Expense expense = ExpenseData.getExpenseById(expenseId);
            if (expense != null) {
                tvAmount.setText("Amount: " + expense.getAmount());
                tvCurrency.setText("Currency: " + expense.getCurrency());
                tvCategory.setText("Category: " + expense.getCategory());
                tvRemark.setText("Remark: " + expense.getRemark());
                tvCreatedDate.setText("Created Date: " + expense.getCreatedDate());
            }
        }

        btnAddNewExpense.setOnClickListener(v -> {
            // Since we are now using fragments, this button should ideally navigate back to the Add Expense tab.
            // For now, it will just finish the activity.
            finish();
        });

        btnBackToHome.setOnClickListener(v -> finish());
    }
}