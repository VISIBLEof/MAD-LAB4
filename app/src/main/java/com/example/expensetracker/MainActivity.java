package com.example.expensetracker;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private Spinner spCategory;
    private TextInputEditText etAmount, etDate, etDescription;
    private Button languageButton, themeButton;

    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spCategory = findViewById(R.id.spCategory);
        etAmount = findViewById(R.id.etAmount);
        etDate = findViewById(R.id.etDate);
        etDescription = findViewById(R.id.etDescription);
        languageButton = findViewById(R.id.language_button);
        themeButton = findViewById(R.id.theme_button);

        // Spinner from resources (auto-localized)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);

        // MaterialDatePicker for date selection
        etDate.setOnClickListener(v -> showDatePicker());

        // Save
        findViewById(R.id.btnSave).setOnClickListener(this::onSave);

        // Set listener for theme button
        themeButton.setOnClickListener(v -> {
            int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });

        // Set listener for language button
        languageButton.setOnClickListener(v -> {
            String currentLanguage = Locale.getDefault().getLanguage();
            if (currentLanguage.equals("km")) {
                setLocale("en");
            } else {
                setLocale("km");
            }
        });
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        recreate();
    }

    private void showDatePicker() {
        MaterialDatePicker<Long> picker = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText(R.string.hint_select_date)
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        picker.addOnPositiveButtonClickListener(selection -> {
            if (selection != null) {
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                cal.setTimeInMillis(selection);
                // Optional: display in local timezone
                cal.setTimeZone(TimeZone.getDefault());
                etDate.setText(dateFormat.format(cal.getTime()));
            }
        });

        picker.show(getSupportFragmentManager(), "date_picker");
    }

    private void onSave(@NonNull View v) {
        String amount = etAmount.getText() == null ? "" : etAmount.getText().toString().trim();
        String date = etDate.getText() == null ? "" : etDate.getText().toString().trim();
        String desc = etDescription.getText() == null ? "" : etDescription.getText().toString().trim();
        String category = spCategory.getSelectedItem() != null ? spCategory.getSelectedItem().toString() : "";

        if (amount.isEmpty()) {
            etAmount.setError(getString(R.string.error_amount_required));
            etAmount.requestFocus();
            return;
        }

        String msg = getString(R.string.saved_message, amount, category, date.isEmpty() ? "—" : date);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();


    }
}