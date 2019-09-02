package com.pchromic.BudgetManager.controller;

import com.pchromic.BudgetManager.domain.operation.Operation;
import com.pchromic.BudgetManager.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


}
