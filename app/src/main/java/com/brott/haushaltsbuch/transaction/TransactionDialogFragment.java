package com.brott.haushaltsbuch.transaction;


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
import com.brott.haushaltsbuch.account.AccountNameEnum;
import com.brott.haushaltsbuch.account.AccountTypeEnum;
import com.brott.haushaltsbuch.data.Transaction;
import com.brott.haushaltsbuch.utilities.HelperMethods;

public class TransactionDialogFragment extends DialogFragment {

    private TransactionViewModel transactionViewModel;

    private Spinner categorySpinner;
    private Spinner accountTypeSpinner;
    private EditText amountEditTxt;
    private EditText noteEditTxt;

    private Button saveBtn;
    private Button cancelBtn;

    private String selectedAccount;
    private String selectedCategory;
    private String note;
    private double amount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_transaction_dialog, container, false);

        categorySpinner = root.findViewById(R.id.category_spinner);
        amountEditTxt = root.findViewById(R.id.amount_edit_text);
        noteEditTxt = root.findViewById(R.id.note_edit_text);
        saveBtn = root.findViewById(R.id.save_button);
        cancelBtn = root.findViewById(R.id.cancel_button);
        accountTypeSpinner = root.findViewById(R.id.account_type_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> accountTypeAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.accountType, android.R.layout.simple_spinner_item);
        accountTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(accountTypeAdapter);

        saveBtn.setOnClickListener(e -> {
            if (HelperMethods.isEmpty(amountEditTxt)) {
                amountEditTxt.setError(getResources().getString(R.string.ungueltigerBetrag));
                return;

            } else {
                if (HelperMethods.isEmpty(noteEditTxt)) {
                    note = "";
                } else {
                    note = noteEditTxt.getText().toString().trim();
                }

                amount = Double.parseDouble(amountEditTxt.getText().toString().trim());

                selectedAccount = accountTypeSpinner.getSelectedItem().toString();
                selectedCategory = categorySpinner.getSelectedItem().toString();

                Transaction transaction = new Transaction(
                        selectedAccount,
                        HelperMethods.determineAccountType(selectedAccount),
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

        return root;
    }
}

