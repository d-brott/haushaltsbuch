package com.brott.haushaltsbuch.transaction;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.brott.haushaltsbuch.R;
import com.brott.haushaltsbuch.data.Transaction;

import java.util.List;

public class TransactionFragment extends Fragment {

    TransactionViewModel transactionViewModel;


    public TransactionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_transaction, container, false);

        ListView list = root.findViewById(R.id.transaction_list_view);

        transactionViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(TransactionViewModel.class);
        transactionViewModel.getAllTransactions().observe(this, new Observer<List<Transaction>>() {
            @Override
            public void onChanged(@Nullable final List<Transaction> allTransactions) {
                if (allTransactions.size() > 0) {

                    TransactionAdapter adapter = new TransactionAdapter(getContext(), allTransactions);
                    list.setAdapter(adapter);
                }
            }
        });

        FloatingActionButton fab = root.findViewById(R.id.new_transaction_fab);
        fab.setOnClickListener(e -> {

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);
            TransactionDialogFragment dialogFragment = new TransactionDialogFragment();
            dialogFragment.show(ft, "dialog");
        });

        return root;
    }

}
