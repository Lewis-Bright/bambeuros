package com.lewisb.bambeuro.controller;

import com.lewisb.bambeuro.entity.User;
import com.lewisb.bambeuro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginInfo() {
        StringBuilder sb = new StringBuilder();
        for (User user : userService.getAllUsers()) {
            sb.append(user.getName());
            sb.append(" - Balance: ");
            sb.append(user.getBambeuroBalance());
            sb.append("\n");
        }
        return sb.toString();
    }
}
