package com.pchromic.BudgetManager.service;

import com.pchromic.BudgetManager.domain.report.Report;
import com.pchromic.BudgetManager.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public Report add(Report report) {
        return repository.save(report);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Report update(Report report) {
        return repository.save(report);
    }

    public List<Report> getAll() {
        return repository.findAll();
    }

    public Report findOne(Long id) {
        Optional<Report> optional = repository.findById(id);
        return optional.orElseThrow(IllegalArgumentException::new);
    }
}
