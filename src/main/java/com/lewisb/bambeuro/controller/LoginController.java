package com.lewisb.bambeuro.controller;

import com.lewisb.bambeuro.entity.User;
import com.lewisb.bambeuro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginInfo() {
        return "Enter details below to login";
    }

    @GetMapping("/users")
    public String getUsers() {
        StringBuilder sb = new StringBuilder();
        for (User user : userService.getAllUsers()) {
            sb.append(user.getName());
            sb.append(" - Balance: ");
            sb.append(user.getBambeuroBalance());
            sb.append("\n");
        }
        return sb.toString();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int userId) {
        Optional<User> user = userService.findById(userId);
        return ResponseEntity.of(user);
    }

//    @PutMapping("")
}
