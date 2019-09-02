package com.pchromic.BudgetManager.repository;

import com.pchromic.BudgetManager.domain.expense.Expense;
import com.pchromic.BudgetManager.enums.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>,
        QuerydslPredicateExecutor<Expense> {

    List<Expense> findByValueBetween(int from, int to);

    List<Expense> findByValueGreaterThanEqual(int from);

    List<Expense> findByValueLessThanEqual(int to);

    List<Expense> findByExpenseType(ExpenseType type);

    List<Expense> findByPaymentDate(LocalDate date);

    List<Expense> findByPaymentDateBetween(LocalDate from, LocalDate to);

    List<Expense> findByPaymentDateAfter(LocalDate from);

    List<Expense> findByPaymentDateBefore(LocalDate to);

    List<Expense> findByDescriptionContaining(String description);


}
