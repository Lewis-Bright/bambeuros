package com.lewisb.bambeuro.controller;

import com.lewisb.bambeuro.entity.User;
import com.lewisb.bambeuro.service.UserService;
import com.lewisb.bambeuro.util.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/userTotal")
    public String loginInfo() {
        return String.valueOf(userService.getAllUsers().size());
    }

    @PostMapping("/user")
    public User createUser(@RequestBody Map<String, String> body) {
        LOGGER.info("Attempting to create user");
        String name = body.containsKey("name") ? body.get("name") : NameGenerator.generateName();
        User user = new User(name);
        userService.saveOrUpdate(user);
        return user;
    }

}
