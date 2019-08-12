package com.pchromic.BudgetManager.domain.operation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOperation is a Querydsl query type for Operation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOperation extends EntityPathBase<Operation> {

    private static final long serialVersionUID = 266811880L;

    public static final QOperation operation = new QOperation("operation");

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final SimplePath<java.util.Currency> currency = createSimple("currency", java.util.Currency.class);

    public final StringPath description = createString("description");

    public final NumberPath<java.math.BigDecimal> endingBalance = createNumber("endingBalance", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.pchromic.BudgetManager.enums.OperationClass> operationClass = createEnum("operationClass", com.pchromic.BudgetManager.enums.OperationClass.class);

    public final DatePath<java.time.LocalDate> operationDate = createDate("operationDate", java.time.LocalDate.class);

    public final EnumPath<com.pchromic.BudgetManager.enums.TransactionType> transType = createEnum("transType", com.pchromic.BudgetManager.enums.TransactionType.class);

    public QOperation(String variable) {
        super(Operation.class, forVariable(variable));
    }

    public QOperation(Path<? extends Operation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOperation(PathMetadata metadata) {
        super(Operation.class, metadata);
    }

}

