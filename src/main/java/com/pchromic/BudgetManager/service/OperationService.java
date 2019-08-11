package com.pchromic.BudgetManager.service;

import com.pchromic.BudgetManager.domain.Operation;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;

public interface OperationService {

    Operation addOperation(Operation operation);
    void removeOperation(Long id);
    List<Operation> getAllOperations();
    Operation updateOperation(Operation operation);
    Operation findOperation(Long id);
    void addOperations(List<Operation> operations);
    Operation getOperationFromRow(Row row);


}
