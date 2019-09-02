package com.pchromic.BudgetManager.repository;

import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.domain.operation.QOperation;
import com.pchromic.BudgetManager.enums.OperationClass;
import com.pchromic.BudgetManager.enums.TransactionType;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long>,
        QuerydslPredicateExecutor<Operation>, QuerydslBinderCustomizer<QOperation>, CustomizedOperationRepository {


    @Override
    default void customize(QuerydslBindings bindings, QOperation root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

    List<Operation> findByOperationDateBetween(LocalDate from, LocalDate to);

    List<Operation> findByOperationDateAfter(LocalDate after);

    List<Operation> findByOperationDateBefore(LocalDate before);

    List<Operation> findByOperationClass(OperationClass operationClass);

    List<Operation> findByTransType(TransactionType type);

    List<Operation> findByOperationClassAndAmountGreaterThanEqual(OperationClass operationClass, BigDecimal amount);

    List<Operation> findByOperationClassAndAmountLessThanEqual(OperationClass operationClass, BigDecimal amount);

    List<Operation> findByDescriptionContaining(String description);


}
