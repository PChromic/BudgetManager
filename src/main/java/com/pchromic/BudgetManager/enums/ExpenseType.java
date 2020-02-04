package com.pchromic.BudgetManager.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExpenseType {
    HOME("Home"),
    FOOD("Food"),
    CHILD_RELATED("Child related"),
    DEBT("Debt"),
    HEALTH("Health"),
    TRANSPORTATION("Transportation"),
    PERSONAL("Personal"),
    PET("Pet"),
    ENTERTAINMENT("Entertainment"),
    OTHER("Other");

    private final String description;

    ExpenseType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
