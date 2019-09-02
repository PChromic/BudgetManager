package com.pchromic.BudgetManager.controller;

import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.service.OperationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OperationController {

    private final OperationService service;

    public OperationController(OperationService operationService) {
        this.service = operationService;
    }

    @PostMapping("/operations")
    void add(@RequestBody Operation operation) {
        service.add(operation);
    }

    @DeleteMapping("/operations/{id}")
    void remove(@RequestParam String id) {
        service.remove(Long.valueOf(id));
    }

    @PutMapping("/operations/{id}")
    void update(@RequestBody Operation operation, @RequestParam Long id) {
        service.update(operation);
    }


    @GetMapping(value = "/operations/{id}")
    Operation one(@PathVariable  String id) {
        return service.findOne(Long.valueOf(id));
    }


    @GetMapping("/operations")
    List<Operation> all() {
        return service.getAll();
    }

    @GetMapping("/operations/income")
    List<Operation> income() {
        return service.getByHighestIncome();
    }

    @GetMapping("/operations/expense")
    List<Operation> expense() {
        return service.getByHighestExpense();
    }


}
