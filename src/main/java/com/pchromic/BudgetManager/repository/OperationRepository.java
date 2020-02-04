package com.pchromic.BudgetManager.repository;

import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.domain.operation.QOperation;
import com.pchromic.BudgetManager.enums.OperationClass;
import com.pchromic.BudgetManager.enums.TransactionType;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long>,
        QuerydslPredicateExecutor<Operation> {

    @Override
    List<Operation> findAll(Predicate predicate);

    @Override
    List<Operation> findAll(Predicate predicate, OrderSpecifier<?>... orders);

    default List<Operation> findByHighestIncome() {
        QOperation operation = QOperation.operation;
        BooleanExpression predicate = operation.operationClass.eq(OperationClass.CREDIT);
        return findAll(predicate);
    }

    default List<Operation> findByHighestExpense() {
        QOperation operation = QOperation.operation;
        BooleanExpression predicate = operation.operationClass.eq(OperationClass.DEBIT);
        return findAll(predicate);
    }

    List<Operation> findByOperationDateBefore(LocalDate before);

    List<Operation> findByOperationDateBetween(LocalDate from, LocalDate to);

    List<Operation> findByOperationDateAfter(LocalDate after);

    List<Operation> findByOperationClass(OperationClass operationClass);

    List<Operation> findByTransType(TransactionType type);

    List<Operation> findByOperationClassAndAmountGreaterThanEqual(OperationClass operationClass, BigDecimal amount);

    List<Operation> findByOperationClassAndAmountLessThanEqual(OperationClass operationClass, BigDecimal amount);

    List<Operation> findByDescriptionContaining(String description);


}
