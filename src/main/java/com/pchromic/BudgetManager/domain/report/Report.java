package com.pchromic.BudgetManager.domain.report;

import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.domain.user.User;
import com.pchromic.BudgetManager.enums.ReportType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "REPORT")
@Data
public class Report implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany( cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable( name = "report_operation",
        joinColumns = @JoinColumn(name = "report_id"),
        inverseJoinColumns = @JoinColumn(name = "operation_id")
    )
    private Set<Operation> operations = new HashSet<>();

 }
