package com.pchromic.BudgetManager.service;

import com.pchromic.BudgetManager.domain.expense.Expense;
import com.pchromic.BudgetManager.enums.ExpenseType;
import com.pchromic.BudgetManager.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense add(Expense expense) {
        return repository.save(expense);
    }


    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Expense update(Expense expense) {
        return repository.save(expense);
    }

    public List<Expense> getAll() {
        return repository.findAll();
    }

    public Expense findOne(Long id) {
        Optional<Expense> optional = repository.findById(id);
        return optional.orElseThrow(IllegalArgumentException::new);
    }


    public List<Expense> findByValueBetween(int from, int to){
        return repository.findByValueBetween(from,to);
    }

    public List<Expense> findByValueGreaterThanEqual(int from){
        return repository.findByValueGreaterThanEqual(from);
    }

    public List<Expense> findByValueLessThanEqual(int to) {
        return repository.findByValueLessThanEqual(to);
    }

    public List<Expense> findByExpenseType(ExpenseType type){
        return repository.findByExpenseType(type);
    }

    public List<Expense> findByPaymentDate(LocalDate date){
        return repository.findByPaymentDate(date);
    }

    public List<Expense> findByPaymentDateBetween(LocalDate from, LocalDate to){
        return repository.findByPaymentDateBetween(from,to);
    }

    public List<Expense> findByPaymentDateAfter(LocalDate from){
        return repository.findByPaymentDateAfter(from);
    }

    public List<Expense> findByPaymentDateBefore(LocalDate to){
        return repository.findByPaymentDateBefore(to);
    }
    public List<Expense> findByDescriptionContaining(String description){
        return repository.findByDescriptionContaining(description);
    }

}
