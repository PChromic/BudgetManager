package com.pchromic.BudgetManager.domain.operation;

import com.pchromic.BudgetManager.enums.OperationClass;
import com.pchromic.BudgetManager.enums.TransactionType;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Date;

@XmlRootElement(name = "operation")
@Entity
@Table(name = "operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operation_date", nullable = false)
    LocalDate operationDate;
    // LocalDate orderDate;

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


    public Operation() {
    }

    public Operation(LocalDate operationDate, OperationClass operationClass, TransactionType transType,
                     BigDecimal amount, Currency currency, BigDecimal endingBalance, String description) {
        this.operationDate = operationDate;
        this.operationClass = operationClass;
        this.transType = transType;
        this.amount = amount;
        this.currency = currency;
        this.endingBalance = endingBalance;
        this.description = description;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }

    public OperationClass getOperationClass() {
        return operationClass;
    }

    public void setOperationClass(OperationClass operationClass) {
        this.operationClass = operationClass;
    }

    public TransactionType getTransType() {
        return transType;
    }

    public void setTransType(TransactionType transType) {
        this.transType = transType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(BigDecimal endingBalance) {
        this.endingBalance = endingBalance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}