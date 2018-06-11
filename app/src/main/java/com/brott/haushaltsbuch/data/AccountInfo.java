package com.brott.haushaltsbuch.data;

import android.arch.persistence.room.ColumnInfo;

import java.util.Objects;

public class AccountInfo implements Comparable<AccountInfo> {

    @ColumnInfo(name = "account_name")
    private String accountName;

    @ColumnInfo(name = "account_type")
    private String accountType;

    @ColumnInfo(name = "amount")
    private double amount;

    public AccountInfo(String accountName, String accountType, double amount) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.amount = amount;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "accountName: " + this.accountName + ", accountType: " + this.accountType + ", amount: " + this.amount;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof AccountInfo)) {
            return false;
        }

        AccountInfo accountInfo = (AccountInfo) o;

        return accountInfo.getAccountType().equals(this.getAccountType()) && accountInfo.getAccountName().equals(this.getAccountName());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + accountName.hashCode();
        result = 31 * result + accountType.hashCode();

        return result;
    }

    @Override
    public int compareTo(AccountInfo aInfo) {
        if (aInfo.getAccountType() == null && this.getAccountType() == null) {
            return 0;
        }
        if (this.getAccountType() == null) {
            return 1;
        }
        if (aInfo.getAccountType() == null) {
            return -1;
        }

        return aInfo.getAccountType().compareTo(this.getAccountType());
    }
}
