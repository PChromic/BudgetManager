package com.pchromic.BudgetManager.domain.operation;

import com.pchromic.BudgetManager.enums.OperationClass;
import com.pchromic.BudgetManager.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

public class OperationSearchCriteria {

    LocalDate operationStartDate;
    LocalDate operationEndDate;
    OperationClass operationClass;
    TransactionType transType;
    BigDecimal amountFrom;
    BigDecimal amountTo;
    Currency currency;

    public OperationSearchCriteria() {
    }

    public OperationSearchCriteria(LocalDate operationStartDate, LocalDate operationEndDate, OperationClass operationClass, TransactionType transType,
                                   BigDecimal amountFrom, BigDecimal amountTo, Currency currency) {
        this.operationStartDate = operationStartDate;
        this.operationEndDate = operationEndDate;
        this.operationClass = operationClass;
        this.transType = transType;
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
        this.currency = currency;
    }

    public LocalDate getOperationStartDate() {
        return operationStartDate;
    }

    public void setOperationStartDate(LocalDate operationStartDate) {
        this.operationStartDate = operationStartDate;
    }

    public LocalDate getOperationEndDate() {
        return operationEndDate;
    }

    public void setOperationEndDate(LocalDate operationEndDate) {
        this.operationEndDate = operationEndDate;
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

    public BigDecimal getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(BigDecimal amountFrom) {
        this.amountFrom = amountFrom;
    }

    public BigDecimal getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(BigDecimal amountTo) {
        this.amountTo = amountTo;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }


    public static final class OperationSearchCriteriaBuilder {
        LocalDate operationStartDate;
        LocalDate operationEndDate;
        OperationClass operationClass;
        TransactionType transType;
        BigDecimal amountFrom;
        BigDecimal amountTo;
        Currency currency;

        private OperationSearchCriteriaBuilder() {
        }

        public static OperationSearchCriteriaBuilder anOperationSearchCriteria() {
            return new OperationSearchCriteriaBuilder();
        }

        public OperationSearchCriteriaBuilder withOperationStartDate(LocalDate operationStartDate) {
            this.operationStartDate = operationStartDate;
            return this;
        }

        public OperationSearchCriteriaBuilder withOperationEndDate(LocalDate operationEndDate) {
            this.operationEndDate = operationEndDate;
            return this;
        }

        public OperationSearchCriteriaBuilder withOperationClass(OperationClass operationClass) {
            this.operationClass = operationClass;
            return this;
        }

        public OperationSearchCriteriaBuilder withTransType(TransactionType transType) {
            this.transType = transType;
            return this;
        }

        public OperationSearchCriteriaBuilder withAmountFrom(BigDecimal amountFrom) {
            this.amountFrom = amountFrom;
            return this;
        }

        public OperationSearchCriteriaBuilder withAmountTo(BigDecimal amountTo) {
            this.amountTo = amountTo;
            return this;
        }

        public OperationSearchCriteriaBuilder withCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public OperationSearchCriteriaBuilder withHighestIncome(BigDecimal amountFrom) {
            this.amountFrom = amountFrom;
            return this;
        }

        public OperationSearchCriteriaBuilder withHighestExpense(BigDecimal amountFrom) {
            this.amountFrom = amountFrom;
            return this;
        }

        public OperationSearchCriteria build() {
            OperationSearchCriteria operationSearchCriteria = new OperationSearchCriteria();
            operationSearchCriteria.setOperationStartDate(operationStartDate);
            operationSearchCriteria.setOperationEndDate(operationEndDate);
            operationSearchCriteria.setOperationClass(operationClass);
            operationSearchCriteria.setTransType(transType);
            operationSearchCriteria.setAmountFrom(amountFrom);
            operationSearchCriteria.setAmountTo(amountTo);
            operationSearchCriteria.setCurrency(currency);
            return operationSearchCriteria;
        }
    }
}
