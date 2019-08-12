package com.pchromic.BudgetManager.repository;

import com.pchromic.BudgetManager.domain.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {


    List<Operation> findByHighestIncome();

    List<Operation> findByHighestExpense();
}
