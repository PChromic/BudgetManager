package com.pchromic.BudgetManager.domain.operation;

import com.pchromic.BudgetManager.domain.user.User;
import com.pchromic.BudgetManager.enums.OperationClass;
import com.pchromic.BudgetManager.enums.TransactionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Entity
@Table(name = "OPERATION")

@Data
@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class Operation {


    public Operation() {
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "operation_date", nullable = false)
    LocalDate operationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_class", nullable = false)
    OperationClass operationClass;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    TransactionType transType;

    @Column(name = "amount", nullable = false)
    BigDecimal amount;

    @Column(name = "currency", nullable = false)
    Currency currency;

    @Column(name = "balance", nullable = false)
    BigDecimal endingBalance;

    @Column(name = "description", nullable = false)
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}