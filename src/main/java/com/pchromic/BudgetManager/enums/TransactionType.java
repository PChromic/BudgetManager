package com.pchromic.BudgetManager.enums;

public enum TransactionType {
    CARD_PAYMENT("Płatność kartą"),
    TRANSFER("Przelew"),
    ACC_TRANSFER("Przelew na rachunek"),
    CARD_FEE("Opłata za kartę"),
    ATM_WITHDRAW("Wypłata z bankomatu");

    private final String description;

    TransactionType(final String description) {
        this.description = description;
    }

    public static TransactionType getByDescription(final String description) {
        for (TransactionType t :
                values()) {
            if (t.description.equals(description))
                return t;
        }
        return null;
/*        return Arrays.stream(TransactionType.values())
                .filter(type -> type.description.(description))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found"));*/
    }
}