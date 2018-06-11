package com.brott.haushaltsbuch.transaction;

public enum TransactionCategoryEnum {

    GROCERIES("Lebensmittel"),
    HOUSING("Wohnung"),
    CAR("Auto"),
    SOCIAL_LIFE("Sozialleben"),
    TRAVEL("Reisen"),
    OTHER("Sonstiges"),
    PRESENT("Geschenk");

    private final String text;

    TransactionCategoryEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
