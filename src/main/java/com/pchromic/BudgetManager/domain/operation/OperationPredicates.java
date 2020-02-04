package com.pchromic.BudgetManager.domain.operation;

import com.querydsl.core.types.Predicate;

import java.time.LocalDate;

public final class OperationPredicates {

    OperationPredicates() {
    }

    public static Predicate hasDateBetween(LocalDate left, LocalDate right) {
        return QOperation.operation.operationDate.between(left, right);
    }


}
