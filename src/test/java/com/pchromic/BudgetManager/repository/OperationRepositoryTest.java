package com.pchromic.BudgetManager.repository;


import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.enums.OperationClass;
import com.pchromic.BudgetManager.enums.TransactionType;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationRepositoryTest {

    @Autowired
    private OperationRepository repository;

    private Operation operation;
    private Operation operation1;

    @Before
    public void setData() {
        operation = new Operation();
        operation.setId(1L);
        operation.setAmount(new BigDecimal(100));
        operation.setTransType(TransactionType.ACC_TRANSFER);
        operation.setOperationDate(LocalDate.now());
        operation.setOperationClass(OperationClass.CREDIT);
        operation.setEndingBalance(BigDecimal.ONE);
        operation.setDescription("123");
        operation.setCurrency(Currency.getInstance("PLN"));
        repository.save(operation);

        operation1 = new Operation();
        operation1.setId(2L);
        operation1.setAmount(new BigDecimal(1010));
        operation1.setTransType(TransactionType.ACC_TRANSFER);
        operation1.setOperationDate(LocalDate.of(2019, 7, 4));
        operation1.setOperationClass(OperationClass.CREDIT);
        operation1.setEndingBalance(BigDecimal.ONE);
        operation1.setDescription("123");
        operation1.setCurrency(Currency.getInstance("PLN"));
        repository.save(operation1);

    }

    @Test
    public void shouldGetByDateBetween() {
        LocalDate from = LocalDate.of(2019, 7, 3);
        LocalDate to = LocalDate.of(2019, 7, 5);
       /* Predicate predicate = OperationPredicates.hasDateBetween(from, to);

        Iterable<Operation> all = repository.findAll(predicate);*/
        List<Operation> between = repository.findByOperationDateBetween(from, to);

        assertThat(between, containsInAnyOrder(operation1));

    }

    @Test
    public void shouldGetByOperationClassAndAmountGreaterThan() {
        OperationClass opClass = OperationClass.DEBIT;
        Currency cur = Currency.getInstance("PLN");
        BigDecimal amount = new BigDecimal(1000);

        List<Operation> between = repository.findByOperationClassAndAmountGreaterThanEqual(opClass, amount);
        repository.findAll();
        assertThat(between, containsInAnyOrder(operation1));

    }

    @Test
    public void shouldGetOperationWithHighestIncome() {

        List<Operation> byHighestExpense = repository.findByHighestIncome();

        Assert.assertEquals(byHighestExpense.size(), 1);
        Assert.assertEquals(1010.00, byHighestExpense.get(0).getAmount());
    }

    @Test
    public void shouldGetOperationsAfterDate() {

        // List<Operation> byHighestExpense = repository.findByOperationDateAfter(LocalDate.of(2019, 8, 4));

        //   Assert.assertEquals(2019 - 07 - 04, byHighestExpense);

    }
}
