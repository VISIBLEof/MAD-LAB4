package com.example.expensetracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.ExpenseData;

import java.util.List;

public class HomeFragment extends Fragment {

    private TextView tvLastExpense;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvLastExpense = view.findViewById(R.id.tvLastExpense);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateLastExpense();
    }

    private void updateLastExpense() {
        List<Expense> expenses = ExpenseData.getExpenses();
        if (!expenses.isEmpty()) {
            Expense lastExpense = expenses.get(expenses.size() - 1);
            String lastExpenseText = lastExpense.getAmount() + " " + lastExpense.getCurrency() + " on " + lastExpense.getCreatedDate();
            tvLastExpense.setText(lastExpenseText);
        } else {
            tvLastExpense.setText("0");
        }
    }
}