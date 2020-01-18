package com.pchromic.BudgetManager.domain.expense;

import com.pchromic.BudgetManager.domain.user.User;
import com.pchromic.BudgetManager.enums.ExpenseType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "EXPENSE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expense implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Enumerated(EnumType.STRING)
        private ExpenseType expenseType;

        @Column(name = "value")
        private int value;

        @Column(name = "payment_date")
        private LocalDate paymentDate;

        @Column(name = "description")
        private String description;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;
}
