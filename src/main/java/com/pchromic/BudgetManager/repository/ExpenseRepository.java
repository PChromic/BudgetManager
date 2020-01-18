package com.pchromic.BudgetManager.repository;

import com.pchromic.BudgetManager.domain.expense.Expense;
import com.pchromic.BudgetManager.domain.expense.QExpense;
import com.pchromic.BudgetManager.enums.ExpenseType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface ExpenseRepository extends JpaRepository<Expense, Long>,
        QuerydslPredicateExecutor<Expense> {

    @Override
    List<Expense> findAll(Predicate predicate);

    @Override
    List<Expense> findAll(Predicate predicate, OrderSpecifier<?>... orders);

    List<Expense> findByValueBetween(int from, int to);

    List<Expense> findByValueGreaterThanEqual(int from);

    List<Expense> findByValueLessThanEqual(int to);

    List<Expense> findByExpenseType(ExpenseType type);

    List<Expense> findByPaymentDate(LocalDate date);

    List<Expense> findByPaymentDateBetween(LocalDate from, LocalDate to);

    List<Expense> findByPaymentDateAfter(LocalDate from);

    List<Expense> findByPaymentDateBefore(LocalDate to);

    List<Expense> findByDescriptionContaining(String description);

    default List<Expense> findByIncomingPaymentDate(){
        QExpense expense = QExpense.expense;
        BooleanExpression isIncoming = expense.paymentDate.after(LocalDate.now());

        return findAll(isIncoming,expense.paymentDate.asc());
    }

    default List<Expense> findByIncomingPaymentDateAndTypes (List<ExpenseType> expenses) {
        QExpense expense = QExpense.expense;
        BooleanExpression isIncoming = expense.paymentDate.after(LocalDate.now());

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(isIncoming);
        for (ExpenseType et : expenses) {
            builder.or(expense.expenseType.eq(et));
        }

        return findAll(builder, expense.paymentDate.asc());

    }




}
