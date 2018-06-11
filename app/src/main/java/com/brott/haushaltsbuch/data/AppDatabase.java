package com.brott.haushaltsbuch.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.brott.haushaltsbuch.account.AccountNameEnum;
import com.brott.haushaltsbuch.account.AccountTypeEnum;
import com.brott.haushaltsbuch.transaction.TransactionCategoryEnum;

@Database(entities = {Transaction.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TransactionDao transactionDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database").build();
                }
            }
        }
        return INSTANCE;
    }
/*
    private  static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TransactionDao tDao;

        PopulateDbAsync(AppDatabase db) {
            tDao = db.transactionDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            Transaction transaction = new Transaction(AccountNameEnum.MONEY.toString(), AccountTypeEnum.ACCOUNT_FOR_PAYMENTS.toString(), TransactionCategoryEnum.OTHER.toString(), "Initial", 0, "01.06.2018");
            tDao.insertTransaction(transaction);
            transaction = new Transaction(AccountNameEnum.CHECK_ACCOUNT.toString(), AccountTypeEnum.ACCOUNT_FOR_PAYMENTS.toString(), TransactionCategoryEnum.OTHER.toString(), "Initial", 0, "01.06.2018");
            tDao.insertTransaction(transaction);
            transaction = new Transaction(AccountNameEnum.SAVINGS_ACCOUNT.toString(), AccountTypeEnum.SAVINGS_ACCOUNT.toString(), TransactionCategoryEnum.OTHER.toString(), "Initial", 0, "01.06.2018");
            tDao.insertTransaction(transaction);
            transaction = new Transaction(AccountNameEnum.PORTFOLIO.toString(), AccountTypeEnum.SAVINGS_ACCOUNT.toString(), TransactionCategoryEnum.OTHER.toString(), "Initial", 0, "01.06.2018");
            tDao.insertTransaction(transaction);

            return null;
        }
    }
    */

}

