package com.pchromic.BudgetManager.service;

import com.pchromic.BudgetManager.domain.operation.Operation;
import org.apache.poi.ss.usermodel.Row;

import java.time.LocalDate;
import java.util.List;

public interface OperationService {

    Operation addOperation(Operation operation);
    void removeOperation(Long id);
    Operation updateOperation(Operation operation);
    List<Operation> getAllOperations();
    Operation findOperation(Long id);
    void addOperations(List<Operation> operations);
    Operation getOperationFromRow(Row row);
    List<Operation> getByHighestIncome();
    List<Operation> getByHighestExpense();
    List<Operation> getByDateBetween(LocalDate from, LocalDate to);
    List<Operation> getByDateAfter(LocalDate after);



}
