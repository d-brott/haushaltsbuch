package com.brott.haushaltsbuch.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.TransactionTooLargeException;
import android.support.annotation.NonNull;

@Entity(tableName = "transaction")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "account_name")
    private String accountName;

    @ColumnInfo(name = "account_type")
    private String accountType;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "note")
    private String note;

    @ColumnInfo(name = "amount")
    private double amount;

    @ColumnInfo(name = "date")
    private String date;

    public Transaction(String accountName, String accountType, String category, String note, double amount, String date) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.category = category;
        this.note = note;
        this.amount = amount;
        this.date = date;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCategory() {
        return category;
    }

    public String getNote() {
        return note;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "accountName: " + this.accountName + ", accountType: " + this.accountType + ", category: " + this.category + ", note: " +
                this.note + ", amount: " + this.amount + ", date: " + this.date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;

        Transaction transaction = (Transaction) o;

        return transaction.getAccountName().equals(this.getAccountName()) && transaction.getAccountType().equals(this.getAccountType()) &&
                (transaction.getId() == this.getId());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result * accountName.hashCode();
        result = 31 * result * accountType.hashCode();
        result = 31 * result * id;

        return result;

    }
}
