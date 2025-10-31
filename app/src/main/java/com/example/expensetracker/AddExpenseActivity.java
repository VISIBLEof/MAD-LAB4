package com.example.expensetracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText etAmount, etRemark, etCreatedDate;
    private Spinner spCurrency, spCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        etAmount = findViewById(R.id.etAmount);
        spCurrency = findViewById(R.id.spCurrency);
        spCategory = findViewById(R.id.spCategory);
        etRemark = findViewById(R.id.etRemark);
        etCreatedDate = findViewById(R.id.etCreatedDate);
        Button btnAddExpense = findViewById(R.id.btnAddExpense);

        // Populate spinners
        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(this,
                R.array.currencies, android.R.layout.simple_spinner_item);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCurrency.setAdapter(currencyAdapter);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(categoryAdapter);

        btnAddExpense.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("amount", etAmount.getText().toString());
            resultIntent.putExtra("currency", spCurrency.getSelectedItem().toString());
            resultIntent.putExtra("category", spCategory.getSelectedItem().toString());
            resultIntent.putExtra("remark", etRemark.getText().toString());
            resultIntent.putExtra("createdDate", etCreatedDate.getText().toString());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}