package com.pchromic.BudgetManager.domain.user;

import com.pchromic.BudgetManager.domain.expense.Expense;
import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.domain.report.Report;
import com.pchromic.BudgetManager.enums.UserType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false, length = 200)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Operation> operations = new HashSet<>();


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
    private Set<Report> reports = new HashSet<>();

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
    private Set<Expense> expenses = new HashSet<>();

    public void addExpense(Expense expense) {
        expenses.add(expense);
        expense.setUser(this);
    }

    public void removeExpense(Expense expense) {
        expenses.remove(expense);
        expense.setUser(null);
    }
}
