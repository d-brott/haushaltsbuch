package com.brott.haushaltsbuch.transaction;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.brott.haushaltsbuch.R;
import com.brott.haushaltsbuch.data.Transaction;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransactionAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Transaction> mDataSource;

    private TextView accountName;
    private TextView accountBalance;
    private TextView transactionCategory;
    private TextView transactionNote;
    private TextView dateOfTransaction;

    private Transaction transaction;

    public TransactionAdapter(Context context, List<Transaction> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View root = mInflater.inflate(R.layout.account_list_view_item, parent, false);

        accountName = root.findViewById(R.id.account_name_text_view);
        accountBalance = root.findViewById(R.id.account_balance_text_view);
        transactionCategory = root.findViewById(R.id.transaction_category_text_view);
        transactionNote = root.findViewById(R.id.transaction_note_text_view);
        dateOfTransaction = root.findViewById(R.id.date_of_transaction_text_view);

        transaction = (Transaction) getItem(position);

        accountName.setText(transaction.getAccountName());
        accountBalance.setText(formatAmount(transaction.getAmount(), accountBalance));
        transactionCategory.setText(transaction.getCategory());
        transactionNote.setText(transaction.getNote());
        dateOfTransaction.setText(formatDate(transaction.getDate()));

        return root;
    }

    private String formatAmount(double amount, TextView textView) {
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedAmount = df.format(amount);

        if (amount < 0) {
            textView.setTextColor(Color.rgb(255, 0, 0));
        } else if (amount > 0) {
            textView.setTextColor(Color.rgb(0, 153, 51));
        }

        return formattedAmount + " €";
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }
}
