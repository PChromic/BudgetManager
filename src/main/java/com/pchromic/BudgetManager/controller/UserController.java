package com.pchromic.BudgetManager.controller;

import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.domain.user.User;
import com.pchromic.BudgetManager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/users")
    void add(@RequestBody User user) {
        service.add(user);
    }

    @DeleteMapping(value = "/users/{id}")
    void remove(@RequestParam String id) {
        service.remove(Long.valueOf(id));
    }

    @PutMapping(value = "/users/{id}")
    void update(@RequestBody User user, @RequestParam Long id) {
        service.update(user);
    }

    @GetMapping(value = "/users/{id}")
    User one(@PathVariable String id) {
        return service.findOne(Long.valueOf(id));
    }

    @GetMapping(value = "/users")
    List<User> all() {
        return service.getAll();
    }


}
