package com.pchromic.BudgetManager.repository;

import com.pchromic.BudgetManager.domain.operation.Operation;

import java.util.List;

public interface CustomizedOperationRepository {

    List<Operation> findByHighestIncome();
    List<Operation> findByHighestExpense();
}
