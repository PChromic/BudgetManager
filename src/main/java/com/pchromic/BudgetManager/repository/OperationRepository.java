package com.pchromic.BudgetManager.repository;

import com.pchromic.BudgetManager.domain.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {


    List<Operation> findByHighestIncome();
    List<Operation> findByHighestExpense();
    List<Operation> findByOperationDateBetween(LocalDate from, LocalDate to);
    List<Operation> findByOperationDateAfter(LocalDate after);

/*    List<Operation> findFirstByOrderByAmountDesc();
    List<Operation> findFirstByOperationClassOrderByAmountDesc();
    List<Operation> findFirstByOperationClass(OperationClass operationClass);*/
}
