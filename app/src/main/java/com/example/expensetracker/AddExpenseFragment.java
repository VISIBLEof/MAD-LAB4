package com.example.expensetracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.ExpenseData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddExpenseFragment extends Fragment {

    private EditText etAmount, etRemark, etCreatedDate;
    private Spinner spCurrency, spCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);

        etAmount = view.findViewById(R.id.etAmount);
        spCurrency = view.findViewById(R.id.spCurrency);
        spCategory = view.findViewById(R.id.spCategory);
        etRemark = view.findViewById(R.id.etRemark);
        etCreatedDate = view.findViewById(R.id.etCreatedDate);
        Button btnAddExpense = view.findViewById(R.id.btnAddExpense);

        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.currencies, android.R.layout.simple_spinner_item);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCurrency.setAdapter(currencyAdapter);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.categories, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(categoryAdapter);

        btnAddExpense.setOnClickListener(v -> {
            String amountStr = etAmount.getText().toString();
            if (amountStr.isEmpty()) {
                Toast.makeText(getContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();
                return;
            }

            double amount = Double.parseDouble(amountStr);
            String currency = spCurrency.getSelectedItem().toString();
            String category = spCategory.getSelectedItem().toString();
            String remark = etRemark.getText().toString();
            String createdDate = etCreatedDate.getText().toString();

            if (createdDate.isEmpty()) {
                createdDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            }

            long id = System.currentTimeMillis();
            Expense newExpense = new Expense(id, amount, currency, category, remark, createdDate);
            ExpenseData.addExpense(newExpense);

            Toast.makeText(getContext(), "Expense added: " + amount + " " + currency, Toast.LENGTH_SHORT).show();

            etAmount.setText("");
            etRemark.setText("");
            etCreatedDate.setText("");
            spCurrency.setSelection(0);
            spCategory.setSelection(0);
        });

        return view;
    }
}