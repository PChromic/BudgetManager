package com.pchromic.BudgetManager.repository;

import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.domain.operation.QOperation;
import com.pchromic.BudgetManager.enums.OperationClass;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CustomizedOperationRepositoryImpl implements CustomizedOperationRepository {

    @PersistenceContext
    protected EntityManager entityManager;

    public List<Operation> findByHighestIncome() {
        QOperation operation = QOperation.operation;

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<Operation> query = queryFactory.selectFrom(operation);

        return query
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
}
