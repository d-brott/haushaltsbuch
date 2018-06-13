package com.brott.haushaltsbuch.transaction;


import android.app.ActionBar;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.brott.haushaltsbuch.R;
import com.brott.haushaltsbuch.data.Transaction;
import com.brott.haushaltsbuch.utilities.EditTextUtilities;
import com.brott.haushaltsbuch.utilities.Utilities;

public class TransactionDialogFragment extends DialogFragment {

    private TransactionViewModel transactionViewModel;

    private Spinner categorySpinner;
    private Spinner accountTypeSpinner;
    private EditText amountEditTxt;
    private EditText noteEditTxt;

    private Button saveBtn;
    private Button cancelBtn;
    private Button expenseBtn;
    private Button incomeBtn;
    private Button transferBtn;

    private String selectedAccount;
    private String selectedCategory;
    private String note;
    private double amount;

    // expense is default
    private boolean isExpense = true;
    private boolean isIncome;
    private boolean isTransfer;

    @Override
    public void onStart() {
        super.onStart();

        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_transaction_dialog, container, false);

        // Spinner
        categorySpinner = root.findViewById(R.id.category_spinner);
        accountTypeSpinner = root.findViewById(R.id.account_type_spinner);

        // EditText
        amountEditTxt = root.findViewById(R.id.amount_edit_text);
        noteEditTxt = root.findViewById(R.id.note_edit_text);

        // Buttons
        saveBtn = root.findViewById(R.id.save_button);
        cancelBtn = root.findViewById(R.id.cancel_button);
        incomeBtn = root.findViewById(R.id.income_btn);
        expenseBtn = root.findViewById(R.id.expense_btn);
        transferBtn = root.findViewById(R.id.transfer_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> accountTypeAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.accountType, android.R.layout.simple_spinner_item);
        accountTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(accountTypeAdapter);

        saveBtn.setOnClickListener(e -> {
            if (EditTextUtilities.isEmpty(amountEditTxt)) {
                amountEditTxt.setError(getResources().getString(R.string.ungueltigerBetrag));
                return;

            } else {
                if (EditTextUtilities.isEmpty(noteEditTxt)) {
                    note = "";
                } else {
                    note = noteEditTxt.getText().toString().trim();
                }

                if (isExpense) {
                    amount = Double.parseDouble("-" + amountEditTxt.getText().toString().trim());
                } else if (isIncome) {
                    amount = Double.parseDouble(amountEditTxt.getText().toString().trim());
                }

                selectedAccount = accountTypeSpinner.getSelectedItem().toString();
                selectedCategory = categorySpinner.getSelectedItem().toString();

                Transaction transaction = new Transaction(
                        selectedAccount,
                        Utilities.determineAccountType(selectedAccount),
                        selectedCategory,
                        note,
                        amount,
                        "30.05.2018");

                transactionViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(TransactionViewModel.class);
                transactionViewModel.insert(transaction);

                getDialog().dismiss();
            }
        });

        cancelBtn.setOnClickListener(e -> {
            getDialog().dismiss();
        });

        incomeBtn.setOnClickListener(e -> {
            isIncome = true;
            isExpense = false;
            isTransfer = false;
        });

        expenseBtn.setOnClickListener(e -> {
            isIncome = false;
            isExpense = true;
            isTransfer = false;
        });

        transferBtn.setOnClickListener(e -> {
            isIncome = false;
            isExpense = false;
            isTransfer = true;
        });

        return root;
    }


}

