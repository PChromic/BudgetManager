package com.pchromic.BudgetManager.service;

import com.pchromic.BudgetManager.domain.operation.Operation;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OperationService {

    Operation addOperation(Operation operation);
    void removeOperation(Long id);
    List<Operation> getAllOperations();
    Operation updateOperation(Operation operation);
    Operation findOperation(Long id);
    void addOperations(List<Operation> operations);
    Operation getOperationFromRow(Row row);


}
