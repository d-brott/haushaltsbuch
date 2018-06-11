package com.brott.haushaltsbuch.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TransactionDao {

    @Query("SELECT * FROM `transaction`")
    LiveData<List<Transaction>> getAllTransactions();

    @Query("SELECT account_name, account_type, SUM(amount) AS amount FROM `transaction` GROUP BY account_name")
    LiveData<List<AccountInfo>> getAmount();

    @Insert
    void insertTransaction(Transaction transaction);
}
