package com.brott.haushaltsbuch.account;

public enum AccountNameEnum {

    MONEY("Bargeld"),
    CHECK_ACCOUNT("Girokonto"),
    SAVINGS_ACCOUNT("Sparguthaben"),
    PORTFOLIO("Depot");

    private final String text;

    AccountNameEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
