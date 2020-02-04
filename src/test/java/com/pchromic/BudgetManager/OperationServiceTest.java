package com.pchromic.BudgetManager;

import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.domain.operation.OperationPredicates;
import com.pchromic.BudgetManager.enums.OperationClass;
import com.pchromic.BudgetManager.enums.TransactionType;
import com.pchromic.BudgetManager.repository.OperationRepository;
import com.querydsl.core.types.Predicate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
public class OperationServiceTest {

    @Autowired
    private OperationRepository repository;

    private Operation operation;
    private Operation operation1;
    private Operation operation2;

    @Before
    public void setData() {
        operation = new Operation();
        operation.setId(1L);
        operation.setAmount(new BigDecimal(100));
        operation.setTransType(TransactionType.ACC_TRANSFER);
        operation.setOperationDate(LocalDate.now());
        operation.setOperationClass(OperationClass.DEBIT);
        operation.setEndingBalance(BigDecimal.ONE);
        operation.setDescription("123");
        operation.setCurrency(Currency.getInstance("PLN"));
        repository.save(operation);

/*        operation1 = new Operation();
        operation1.setId(2L);
        operation1.setAmount(new BigDecimal(1010));
        operation1.setTransType(TransactionType.ACC_TRANSFER);
        operation1.setOperationDate(LocalDate.of(2019, 7, 4));
        operation1.setOperationClass(OperationClass.DEBIT);
        operation1.setEndingBalance(BigDecimal.ONE);
        operation1.setDescription("123");
        operation1.setCurrency(Currency.getInstance("PLN"));
        repository.save(operation1);*/

        operation2 = new Operation();
        operation2.setId(1L);
        operation2.setAmount(new BigDecimal(100));
        operation2.setTransType(TransactionType.ACC_TRANSFER);
        operation2.setOperationDate(LocalDate.now());
        operation2.setOperationClass(OperationClass.DEBIT);
        operation2.setEndingBalance(BigDecimal.ONE);
        operation2.setDescription("123");
        operation2.setCurrency(Currency.getInstance("PLN"));
        repository.save(operation2);

    }

    @Test
    public void shouldAvoidDuplicates() {

        // then
        Assert.assertEquals(1, repository.findAll().size());

    }

    @Test
    public void shouldGetOperationWithHighestIncome() {
        LocalDate from = LocalDate.of(2019, 7, 5);
        LocalDate to = LocalDate.of(2019, 7, 3);
        Predicate predicate = OperationPredicates.hasDateBetween(from, to);
        Iterable<Operation> all = repository.findAll(predicate);

        //   final OperationPredicatesBuilder builder = new OperationPredicatesBuilder().with("amount", ":", "1010");


        // final Iterable<Operation> results = repository.findAll(builder.build());
        assertThat(all, containsInAnyOrder(operation1));


        // assertThat(results, containsInAnyOrder(operation1));

/*        Assert.assertEquals(byHighestIncome.size(),1);
        Assert.assertEquals(new BigDecimal("1000.00"),byHighestIncome.get(0).getAmount());*/
    }
}