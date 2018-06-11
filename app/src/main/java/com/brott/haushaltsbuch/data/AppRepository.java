package com.brott.haushaltsbuch.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class AppRepository {
    private TransactionDao transactionDao;

    private LiveData<List<Transaction>> allTransactions;
    private LiveData<List<AccountInfo>> amount;

    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        transactionDao = db.transactionDao();
        allTransactions = transactionDao.getAllTransactions();
        amount = transactionDao.getAmount();
    }

    public LiveData<List<Transaction>> loadAllTransactions() {
        return allTransactions;
    }

    public LiveData<List<AccountInfo>> getAmount() {
        return amount;
    }


    public void insert(Transaction transaction) {
        new insertTransactionAsyncTask(transactionDao).execute(transaction);
    }

    /*
        private static class insertAsyncTask extends AsyncTask<Account, Void, Void> {

            private AccountDao asyncTaskDao;

            insertAsyncTask(AccountDao dao) {
                asyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(final Account... params) {
                asyncTaskDao.insertAccount(params[0]);
                return null;
            }
        }
    */
    private static class insertTransactionAsyncTask extends AsyncTask<Transaction, Void, Void> {

        private TransactionDao asyncTaskDao;

        insertTransactionAsyncTask(TransactionDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Transaction... params) {
            asyncTaskDao.insertTransaction(params[0]);
            return null;
        }
    }
}
