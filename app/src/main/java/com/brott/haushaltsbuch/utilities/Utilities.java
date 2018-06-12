package com.brott.haushaltsbuch.utilities;

import android.widget.EditText;

import com.brott.haushaltsbuch.account.AccountNameEnum;
import com.brott.haushaltsbuch.account.AccountTypeEnum;

import java.text.NumberFormat;
import java.util.Locale;

public class HelperMethods {

    /**
     * transforms parameter into properly formatted currency
     *
     * @param amount
     * @return euro currency
     */
    public static String formatAmount(double amount) {
        Locale locale = new Locale("deu", "DE");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        String currency = currencyFormatter.format(amount);

        return currency;
    }

    public static boolean isEmpty(EditText editText) {
        String input = editText.getText().toString().trim();
        return input.length() == 0;
    }

    public static String determineAccountType(String accountName) {
        String accountType = "";

        if (accountName.equals(AccountNameEnum.MONEY.toString())) {
            accountType = AccountTypeEnum.ACCOUNT_FOR_PAYMENTS.toString();

        } else if (accountName.equals(AccountNameEnum.CHECK_ACCOUNT.toString())) {
            accountType = AccountTypeEnum.ACCOUNT_FOR_PAYMENTS.toString();

        } else if (accountName.equals(AccountNameEnum.SAVINGS_ACCOUNT.toString())) {
            accountType = AccountTypeEnum.SAVINGS_ACCOUNT.toString();

        } else if (accountName.equals(AccountNameEnum.PORTFOLIO.toString())) {
            accountType = AccountTypeEnum.SAVINGS_ACCOUNT.toString();

        }

        return accountType;
    }
}
