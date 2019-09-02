package com.pchromic.BudgetManager.domain.user;

import com.pchromic.BudgetManager.domain.expense.Expense;
import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.domain.report.Report;
import com.pchromic.BudgetManager.enums.UserType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@Data
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String userName;

    @Column(nullable = false, length = 200)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Operation> operations = new ArrayList<>();

    public void addOperation(Operation operation) {
        operations.add(operation);
        operation.setUser(this);
    }

    public void removeOperation(Operation operation) {
        operations.remove(operation);
        operation.setUser(null);
    }

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Report> reports = new ArrayList<>();

    public void addReport(Report report) {
        reports.add(report);
        report.setUser(this);
    }

    public void removeReport(Report report) {
        reports.remove(report);
        report.setUser(null);
    }

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Expense> expenses = new ArrayList<>();

    public void addExpense(Expense expense) {
        expenses.add(expense);
        expense.setUser(this);
    }

    public void removeExpense(Expense expense) {
        expenses.remove(expense);
        expense.setUser(null);
    }
}
