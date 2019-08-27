package com.pchromic.BudgetManager.repository.impl;

import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.domain.operation.OperationSearchCriteria;
import com.pchromic.BudgetManager.domain.QOperation;
import com.pchromic.BudgetManager.enums.OperationClass;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OperationRepositoryImpl  {

    @PersistenceContext
    protected EntityManager entityManager;

    public List<Operation> findOperationByCriteria(OperationSearchCriteria criteria) {

        CriteriaBuilder cb =
                entityManager.getCriteriaBuilder();

        CriteriaQuery<Operation> query =
                cb.createQuery(Operation.class);

        Root<Operation> root =
                query.from(Operation.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getAmountFrom() != null) {
            predicates.add(cb.ge(root.get("amount"), criteria.getAmountFrom()));
        }

        if (criteria.getAmountTo() != null) {
            predicates.add(cb.le(root.get("amount"), criteria.getAmountFrom()));
        }

        if (criteria.getOperationStartDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(
                    root.get("operation_date"),
                    criteria.getOperationStartDate()));
        }

        if (criteria.getOperationEndDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(
                    root.get("operation_date"),
                    criteria.getOperationEndDate()));
        }

        if (criteria.getTransType() != null) {
            predicates.add(cb.equal(
                    root.get("transaction_type"),
                    criteria.getTransType()));
        }

        if (criteria.getOperationClass() != null) {
            predicates.add(cb.equal(
                    root.get("operation_class"),
                    criteria.getOperationClass()));
        }

        if (criteria.getCurrency() != null) {
            predicates.add(cb.equal(
                    root.get("currency"),
                    criteria.getCurrency()));
        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(query).getResultList();
    }

    public List<Operation> findByHighestIncome() {
        QOperation operation = QOperation.operation;

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<Operation> query = queryFactory.selectFrom(operation);
        return queryFactory.selectFrom(operation)
                .where(operation.operationClass.eq(OperationClass.CREDIT))
                .orderBy(operation.amount.desc())
                .limit(1)
                .fetch();
    }

    public List<Operation> findByHighestExpense() {
        QOperation operation = QOperation.operation;

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<Operation> query = queryFactory.selectFrom(operation);
        return queryFactory.selectFrom(operation)
                .where(operation.operationClass.eq(OperationClass.DEBIT))
                .orderBy(operation.amount.desc())
                .limit(1)
                .fetch();
    }



    public List<Operation> findByOperationDateBetween(LocalDate from, LocalDate to) {
        QOperation operation = QOperation.operation;

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<Operation> query = queryFactory.selectFrom(operation);
        return queryFactory.selectFrom(operation)
                .where(operation.operationDate.between(from, to))
                .orderBy(operation.operationDate.desc())
                .fetch();
    }

}
