package com.pchromic.BudgetManager.enums;

import java.util.Arrays;

public enum ColumnName {

    OPERATION_DATE("Data operacji"),
    ORDER_DATE("Data zlecenia"),
    TRANS_TYPE("Typ transakcji"),
    AMOUNT("Kwota"),
    CURRENCY("Waluta"),
    SALDO("Saldo po transakcji"),
    ACC_NUMBER("Rachunek nadawcy/odbiorcy"),
    SENDER_NAME("Nazwa nadawcy/odbiorcy"),
    DESCRIPT("Opis transakcji");

    private final String description;

    ColumnName(final String description) {
        this.description = description;
    }

    public static boolean isPresent(final String description) {
        return Arrays.stream(ColumnName.values())
                .anyMatch(name -> name.description.equals(description));
    }

    public static ColumnName getByDescription(final String description) {
        return Arrays.stream(ColumnName.values())
                .filter(type -> type.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}