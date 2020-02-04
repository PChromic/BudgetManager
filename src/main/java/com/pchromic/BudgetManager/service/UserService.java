package com.pchromic.BudgetManager.service;

import com.pchromic.BudgetManager.domain.user.User;
import com.pchromic.BudgetManager.enums.UserType;
import com.pchromic.BudgetManager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public User add(User user) {
        return repository.save(user);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public User update(User user) {
        return repository.save(user);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User findOne(Long id) {
        Optional<User> optional = repository.findById(id);
        return optional.orElseThrow(IllegalArgumentException::new);
    }

    public List<User> getByUserType(UserType userType) {
        return repository.getByUserType(userType);
    }

}
