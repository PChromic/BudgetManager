package com.pchromic.BudgetManager.domain.operation;

import com.pchromic.BudgetManager.domain.report.Report;
import com.pchromic.BudgetManager.domain.user.User;
import com.pchromic.BudgetManager.enums.OperationClass;
import com.pchromic.BudgetManager.enums.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "OPERATION")
public class Operation {

    @Id
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

       public Operation() {
    }

    public Operation(Long id, LocalDate operationDate, OperationClass operationClass, TransactionType transType,
                     BigDecimal amount, Currency currency, BigDecimal endingBalance, String description) {
        this.id = id;
        this.operationDate = operationDate;
        this.operationClass = operationClass;
        this.transType = transType;
        this.amount = amount;
        this.currency = currency;
        this.endingBalance = endingBalance;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}