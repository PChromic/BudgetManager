package com.pchromic.BudgetManager.repository;

import com.pchromic.BudgetManager.domain.user.User;
import com.pchromic.BudgetManager.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> getByUserType(UserType userType);
}
