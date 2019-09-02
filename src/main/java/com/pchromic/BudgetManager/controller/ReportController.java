package com.pchromic.BudgetManager.controller;

import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.domain.report.Report;
import com.pchromic.BudgetManager.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {

    private ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @PostMapping("/reports")
    void add(@RequestBody Report report) {
        service.add(report);
    }

    @DeleteMapping("/reports/{id}")
    void remove(@RequestParam String id) {
        service.remove(Long.valueOf(id));
    }

    @PutMapping("/reports/{id}")
    void update(@RequestBody Report report, @RequestParam Long id) {
        service.update(report);
    }


    @GetMapping(value = "/reports/{id}")
    Report one(@PathVariable  String id) {
        return service.findOne(Long.valueOf(id));
    }


    @GetMapping("/reports")
    List<Report> all() {
        return service.getAll();
    }
}
