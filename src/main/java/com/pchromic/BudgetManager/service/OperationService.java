package com.pchromic.BudgetManager.service;

import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.enums.OperationClass;
import com.pchromic.BudgetManager.enums.TransactionType;
import com.pchromic.BudgetManager.repository.OperationRepository;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class OperationService {

    private final OperationRepository repository;

    public OperationService(OperationRepository operationRepository) {
        this.repository = operationRepository;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ REPOSITORY ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */


    public void addOperations(List<Operation> operations) {
        for (Operation o :
                operations) {
            repository.save(o);
        }
    }


    public Operation add(Operation operation) {
        return repository.save(operation);
    }


    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Operation update(Operation operation) {
        return repository.save(operation);
    }

    public List<Operation> getAll() {
        return repository.findAll();
    }

    public Operation findOne(Long id) {
        Optional<Operation> optional = repository.findById(id);
        return optional.orElseThrow(IllegalArgumentException::new);
    }

    public List<Operation> getByHighestIncome() {
        return repository.findByHighestIncome();

    }

    public List<Operation> getByHighestExpense() {
        return repository.findByHighestExpense();
    }


    public List<Operation> getByDateBetween(LocalDate from, LocalDate to) {
        return repository.findByOperationDateBetween(from, to);
    }

    public List<Operation> getByDateAfter(LocalDate after) {
        return repository.findByOperationDateAfter(after);
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ FILES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    public Operation getOperationFromRow(Row row) {

        String transType;
        TransactionType type;

        transType = row.getCell(3).getStringCellValue();
        type = getTransactionType(transType);

      /*  id = Long.valueOf(row.getCell(0).getStringCellValue());
        operation.setId(id);

        LocalDate operationDate = convertToLocalDateViaInstant(row.getCell(1).getDateCellValue());
        operation.setOperationDate(operationDate);



        amount = BigDecimal.valueOf(row.getCell(4).getNumericCellValue()).abs();
        operation.setAmount(amount);

        Currency cur = Currency.getInstance(row.getCell(5).getStringCellValue());
        operation.setCurrency(cur);

        balance = BigDecimal.valueOf(row.getCell(6).getNumericCellValue());
        operation.setEndingBalance(balance);

        stringValue = row.getCell(8).getStringCellValue();
        operation.setDescription(stringValue);

        operation.setOperationClass(setOperationClass(amount));*/

        return Operation.builder()
                .id(Long.valueOf(row.getCell(0).getStringCellValue()))
                .operationDate(convertToLocalDateViaInstant(row.getCell(1).getDateCellValue()))
                .operationClass(setOperationClass(BigDecimal.valueOf(row.getCell(4).getNumericCellValue()).abs()))
                .transType(type)
                .amount(BigDecimal.valueOf(row.getCell(4).getNumericCellValue()).abs())
                .currency(Currency.getInstance(row.getCell(5).getStringCellValue()))
                .endingBalance(BigDecimal.valueOf(row.getCell(6).getNumericCellValue()))
                .description(row.getCell(8).getStringCellValue())
                .build();
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
        return money.signum() > 0 ? OperationClass.CREDIT : OperationClass.DEBIT;
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