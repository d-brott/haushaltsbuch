package com.brott.haushaltsbuch.transaction;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.brott.haushaltsbuch.data.AppRepository;
import com.brott.haushaltsbuch.data.Transaction;

import java.util.List;

import android.arch.lifecycle.AndroidViewModel;

public class TransactionViewModel extends AndroidViewModel {

    private AppRepository repository;
    private LiveData<List<Transaction>> allTransactions;

    public TransactionViewModel(Application application) {
        super(application);
        repository = new AppRepository(application);
        allTransactions = repository.loadAllTransactions();
    }

    LiveData<List<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    public void insert(Transaction transaction) {
        repository.insert(transaction);
    }

}
