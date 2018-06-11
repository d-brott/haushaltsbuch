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

public class TransactionDialogFragment extends DialogFragment {

    private TransactionViewModel transactionViewModel;

    private Spinner categorySpinner;
    private EditText amountEditTxt;
    private EditText noteEditTxt;

    private Button saveBtn;
    private Button moneyBtn;
    private Button checkAccountBtn;
    private Button savingsBtn;
    private Button portfolioBtn;

    private String accountName = "";
    private String note = "";
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
        moneyBtn = root.findViewById(R.id.money_btn);
        checkAccountBtn = root.findViewById(R.id.check_account_btn);
        savingsBtn = root.findViewById(R.id.savings_btn);
        portfolioBtn = root.findViewById(R.id.portfolio_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        saveBtn.setOnClickListener(e -> {

            amount = Double.parseDouble(amountEditTxt.getText().toString());
            note = noteEditTxt.getText().toString();

            Transaction transaction = new Transaction(accountName, AccountTypeEnum.ACCOUNT_FOR_PAYMENTS.toString(), "Lebensmittel", note, amount, "30.05.2018");

            transactionViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(TransactionViewModel.class);
            transactionViewModel.insert(transaction);

            getDialog().dismiss();
        });

        moneyBtn.setOnClickListener(e -> {
            accountName = AccountNameEnum.MONEY.toString();
        });

        savingsBtn.setOnClickListener(e -> {
            accountName = AccountNameEnum.SAVINGS_ACCOUNT.toString();
        });

        portfolioBtn.setOnClickListener(e -> {
            accountName = AccountNameEnum.PORTFOLIO.toString();
        });

        checkAccountBtn.setOnClickListener(e -> {
            accountName = AccountNameEnum.CHECK_ACCOUNT.toString();
        });

        return root;
    }
}

