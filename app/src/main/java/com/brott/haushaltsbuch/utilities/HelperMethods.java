package com.brott.haushaltsbuch.utilities;

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
}
