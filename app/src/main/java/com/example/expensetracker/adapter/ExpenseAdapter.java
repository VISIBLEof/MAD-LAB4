package com.example.expensetracker.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.ExpenseDetailActivity;
import com.example.expensetracker.R;
import com.example.expensetracker.model.Expense;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private final Context context;
    private final List<Expense> expenseList;

    public ExpenseAdapter(Context context, List<Expense> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expense, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.tvAmount.setText(String.valueOf(expense.getAmount()));
        holder.tvCurrency.setText(expense.getCurrency());
        holder.tvCategory.setText(expense.getCategory());
        holder.tvRemark.setText(expense.getRemark());
        holder.tvCreatedDate.setText(expense.getCreatedDate());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ExpenseDetailActivity.class);
            intent.putExtra("expenseId", expense.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView tvAmount, tvCurrency, tvCategory, tvRemark, tvCreatedDate;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvCurrency = itemView.findViewById(R.id.tvCurrency);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvRemark = itemView.findViewById(R.id.tvRemark);
            tvCreatedDate = itemView.findViewById(R.id.tvCreatedDate);
        }
    }
}