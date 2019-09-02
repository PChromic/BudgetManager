package com.pchromic.BudgetManager.repository;

import com.pchromic.BudgetManager.domain.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {


}
