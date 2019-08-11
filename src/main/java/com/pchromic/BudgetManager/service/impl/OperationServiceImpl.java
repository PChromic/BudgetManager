package com.pchromic.BudgetManager.service.impl;

import com.pchromic.BudgetManager.domain.Operation;
import com.pchromic.BudgetManager.enums.OperationClass;
import com.pchromic.BudgetManager.enums.TransactionType;
import com.pchromic.BudgetManager.repository.OperationRepository;
import com.pchromic.BudgetManager.service.OperationService;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.rmi.NoSuchObjectException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;

    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ REPOSITORY ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    List<Operation> operations = new ArrayList<>();

    public void addOperations(List<Operation> operations) {
        for (Operation o :
                operations) {
            operationRepository.save(o);
        }
    }

    public Operation addOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    public void removeOperation(Long id) {
        operationRepository.deleteById(id);
    }

    public Operation updateOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    public Operation findOperation(Long id) {
        Optional<Operation> operationOptional = operationRepository.findById(id);
        return operationOptional.orElseThrow(IllegalArgumentException::new);
    }

    public List<Operation> getOperationsWithinDates(Date startDate, Date endDate){

    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ FILES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    public Operation getOperationFromRow(Row row) {
        String stringValue;
        BigDecimal money;
        Operation operation = new Operation();

        LocalDate operationDate = convertToLocalDateViaInstant(row.getCell(1).getDateCellValue());
        operation.setOperationDate(operationDate);

        stringValue = row.getCell(3).getStringCellValue();
        operation.setTransType(getTransactionType(stringValue));

        money = BigDecimal.valueOf(row.getCell(4).getNumericCellValue());
        operation.setAmount(money);

        Currency cur = Currency.getInstance(row.getCell(5).getStringCellValue());
        operation.setCurrency(cur);

        money = BigDecimal.valueOf(row.getCell(6).getNumericCellValue());
        operation.setEndingBalance(money);

        stringValue = row.getCell(8).getStringCellValue();
        operation.setDescription(stringValue);

        operation.setOperationClass(setOperationClass(money));

        return operation;
        //operations.add(operation);
    }

    private TransactionType getTransactionType(String transactionType) {
        TransactionType type = TransactionType.getByDescription(transactionType);

        switch (type) {

            case TRANSFER:
                return TransactionType.TRANSFER;
            case CARD_PAYMENT:
                return TransactionType.CARD_PAYMENT;
            case ATM_WITHDRAW:
                return TransactionType.ATM_WITHDRAW;
            case CARD_FEE:
                return TransactionType.CARD_FEE;
            case ACC_TRANSFER:
                return TransactionType.ACC_TRANSFER;
            default:
                throw new IllegalArgumentException("This type of transaction is not supported");
        }

    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private OperationClass setOperationClass(BigDecimal money) {
        return money.intValue() > 0 ? OperationClass.CREDIT : OperationClass.DEBIT;
    }

    private LocalDate parseStringToLocalDate(String date) {
        return LocalDate.parse(date);
    }

    private String stringToBigDecimalFormat(String s) {
        String amountString = s;
        amountString = amountString.replaceAll(",", ".");
        amountString = amountString.replaceAll("\\s+", "");
        return amountString;
    }
}