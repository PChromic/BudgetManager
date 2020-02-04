package com.pchromic.BudgetManager.service;

import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.enums.OperationClass;
import com.pchromic.BudgetManager.enums.TransactionType;
import com.pchromic.BudgetManager.repository.OperationRepository;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class OperationService {

    private final OperationRepository repository;

    // for test purposes
    int i = 0;

    public OperationService(OperationRepository operationRepository) {
        this.repository = operationRepository;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ REPOSITORY ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */


    public void addOperations(Set<Operation> operations) {
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
/*        List<Operation> operations = repository.findAll();
        Collections.sort(operations, Collections.reverseOrder());
        return operations;*/

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

    public Page<Operation> findPaginatedOperations(int page, int size) {
        return repository.findAll(new PageRequest(page, size));
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ FILES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

    public Operation getOperationFromRow(Row row) {

        String transType;
        TransactionType type;

        transType = row.getCell(3).getStringCellValue();
        type = getTransactionType(transType);


        return Operation.builder()
                .id(Long.valueOf(row.getCell(0).getStringCellValue()))
                .operationDate(convertToLocalDateViaInstant(row.getCell(1).getDateCellValue()))
                .operationClass(setOperationClass(BigDecimal.valueOf(row.getCell(4).getNumericCellValue())))
                .transType(type)
                .amount(BigDecimal.valueOf(row.getCell(4).getNumericCellValue()).abs())
                .currency(Currency.getInstance(row.getCell(5).getStringCellValue()))
                .endingBalance(BigDecimal.valueOf(row.getCell(6).getNumericCellValue()))
                .description(row.getCell(8).getStringCellValue())
                .build();
    }

    private TransactionType getTransactionType(String transactionType) {
        TransactionType type = TransactionType.getByDescription(transactionType);
        System.out.println(i++);
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
            case REFUND:
                return TransactionType.REFUND;
            case CORRECTION:
                return TransactionType.CORRECTION;
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


}