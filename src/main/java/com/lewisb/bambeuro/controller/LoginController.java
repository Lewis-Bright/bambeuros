package com.lewisb.bambeuro.controller;

import com.lewisb.bambeuro.entity.PaymentTransaction;
import com.lewisb.bambeuro.entity.User;
import com.lewisb.bambeuro.service.UserService;
import com.lewisb.bambeuro.transaction.TransactionHandler;
import com.lewisb.bambeuro.util.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginInfo() {
        return "Enter details below to login";
    }

    @PostMapping("/user")
    public User createUser(@RequestBody Map<String, String> body) {
        String name = body.containsKey("name") ? body.get("name") : NameGenerator.generateName();
        User user = new User(name);
        userService.saveOrUpdate(user);
        return user;
    }

}
