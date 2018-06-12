package com.brott.haushaltsbuch.utilities;

import com.brott.haushaltsbuch.account.AccountNameEnum;
import com.brott.haushaltsbuch.account.AccountTypeEnum;

import java.text.NumberFormat;
import java.util.Locale;

public class Utilities {

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

    /**
     * determines the correct account type based on the passed in parameter
     *
     * @param accountName
     * @return accountType
     */
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
