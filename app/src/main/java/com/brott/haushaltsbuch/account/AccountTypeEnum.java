package com.brott.haushaltsbuch.account;

public enum AccountTypeEnum {

    ACCOUNT_FOR_PAYMENTS("Zahlungskonto"),
    SAVINGS_ACCOUNT("Sparkonto");

    private final String text;

    AccountTypeEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}


