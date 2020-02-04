package com.pchromic.BudgetManager.controller;

import com.pchromic.BudgetManager.domain.expense.Expense;
import com.pchromic.BudgetManager.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping("/expenses")
    void add(@RequestBody Expense expense) {
        service.add(expense);
    }

    @DeleteMapping("/expenses/{id}")
    void remove(@RequestParam String id) {
        service.remove(Long.valueOf(id));
    }

    @PutMapping("/expenses/{id}")
    void update(@RequestBody Expense expense, @RequestParam Long id) {
        service.update(expense);
    }


    @GetMapping(value = "/expenses/{id}")
    Expense one(@PathVariable String id) {
        return service.findOne(Long.valueOf(id));
    }


    @GetMapping("/expenses")
    List<Expense> all() {
        return service.getAll();
    }
}
