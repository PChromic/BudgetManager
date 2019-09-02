package com.pchromic.BudgetManager.enums;

import java.util.Arrays;


public enum OperationClass {
    CREDIT("CREDIT"),
    DEBIT("DEBIT");

    private final String operation;

    OperationClass(final String operation) {
        this.operation = operation;
    }

    public static OperationClass getByDescription(final String operation) {
        return Arrays.stream(OperationClass.values())
                .filter(type -> type.operation.equals(operation))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}
