package com.pchromic.BudgetManager.repository;

import com.pchromic.BudgetManager.domain.expense.Expense;
import com.pchromic.BudgetManager.enums.ExpenseType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpenseRepositoryTest {

    @Autowired
    private ExpenseRepository repository;

    @Before
    public void setUp() throws Exception {
        Expense expense = Expense.builder()
                .id(1L)
                .paymentDate(LocalDate.of(2019,10,01))
                .expenseType(ExpenseType.DEBT)
                .build();

        repository.save(expense);

        Expense expense1 = Expense.builder()
                .id(2L)
                .paymentDate(LocalDate.of(2019,10,02))
                .expenseType(ExpenseType.ENTERTAINMENT)
                .build();

        repository.save(expense1);
    }

    @Test
    public void findByIncomingExpenses() {
        List<Expense> expenses = repository.findByIncomingPaymentDate();

        Assert.assertEquals(2,expenses.size());
    }

    @Test
    public void findByIncomingExpensesAndTypes() {
        List<ExpenseType> types = new ArrayList<>(Arrays.asList(ExpenseType.DEBT,ExpenseType.ENTERTAINMENT));

        List<Expense> expenses = repository.findByIncomingPaymentDateAndTypes(types);

        Assert.assertEquals(2,expenses.size());

    }


    @Test
    public void findByValueBetween() {
    }

    @Test
    public void findByValueGreaterThanEqual() {
    }

    @Test
    public void findByValueLessThanEqual() {
    }

    @Test
    public void findByExpenseType() {
    }

    @Test
    public void findByPaymentDate() {
    }

    @Test
    public void findByPaymentDateBetween() {
    }

    @Test
    public void findByPaymentDateAfter() {
    }

    @Test
    public void findByPaymentDateBefore() {
    }

    @Test
    public void findByDescriptionContaining() {
    }

}