package com.pchromic.BudgetManager.controller;

import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.service.OperationService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OperationController {

    private final OperationService service;

    public OperationController(OperationService operationService) {
        this.service = operationService;
    }

    @PostMapping(value = "/operations")
    void add(@RequestBody Operation operation) {
        service.add(operation);
    }

    @DeleteMapping(value = "/operations/{id}")
    void remove(@RequestParam String id) {
        service.remove(Long.valueOf(id));
    }

    @PutMapping(value = "/operations/{id}")
    void update(@RequestBody Operation operation, @RequestParam Long id) {
        service.update(operation);
    }


    @GetMapping(value = "/operations/{id}")
    Operation one(@PathVariable  String id) {
        return service.findOne(Long.valueOf(id));
    }

    @GetMapping(value = "/operations/params")
    @ResponseBody
    List<Operation> byDate(@RequestParam String date) {
        System.out.println("searching for operations within given date" + date);
        return service.getByDateAfter(LocalDate.parse(date));
    }

    @GetMapping(value = "/operations")
    List<Operation> all() {
        return service.getAll();
    }

    @GetMapping(value = "/operations/income")
    List<Operation> income() {
        return service.getByHighestIncome();
    }

    @GetMapping(value = "/operations/expense")
    List<Operation> expense() {
        return service.getByHighestExpense();
    }

    // pagination attempt
    @RequestMapping(
            value = "/operations",
            params = { "page", "size" },
            method = RequestMethod.GET
    )
    public Page<Operation> findPaginatedOperations(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        Page<Operation> resultPage = service.findPaginatedOperations(page, size);
        if (page > resultPage.getTotalPages()) {
            throw new NullPointerException();

        }

        return resultPage;
    }

}
