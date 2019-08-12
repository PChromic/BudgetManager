package com.pchromic.BudgetManager;


import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.enums.OperationClass;
import com.pchromic.BudgetManager.enums.TransactionType;
import com.pchromic.BudgetManager.repository.OperationRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationRepositoryTest {

    @Autowired
    private OperationRepository repository;

    @Before
    public void setData(){
        Operation operation = new Operation();
        operation.setAmount(new BigDecimal(100));
        operation.setTransType(TransactionType.ACC_TRANSFER);
        operation.setOperationDate(LocalDate.now());
        operation.setOperationClass(OperationClass.DEBIT);
        operation.setEndingBalance(BigDecimal.ONE);
        operation.setDescription("123");
        operation.setCurrency(Currency.getInstance("PLN"));
        repository.save(operation);

        Operation operation1 = new Operation();
        operation1.setAmount(new BigDecimal(1000));
        operation1.setTransType(TransactionType.ACC_TRANSFER);
        operation1.setOperationDate(LocalDate.now());
        operation1.setOperationClass(OperationClass.DEBIT);
        operation1.setEndingBalance(BigDecimal.ONE);
        operation1.setDescription("123");
        operation1.setCurrency(Currency.getInstance("PLN"));
        repository.save(operation1);

    }

    @Test
    public void shouldGetOperationWithHighestIncome(){

        List<Operation> byHighestIncome = repository.findByHighestIncome();

        Assert.assertEquals(byHighestIncome.size(),1);
        Assert.assertEquals(100,byHighestIncome.get(0).getAmount());
    }
    @Test
    public void shouldGetOperationWithHighestExpense(){

        List<Operation> byHighestExpense = repository.findByHighestExpense();

        Assert.assertEquals(byHighestExpense.size(),1);
        Assert.assertEquals(1000,byHighestExpense.get(0).getAmount());
    }
}
