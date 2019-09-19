package com.lewisb.bambeuro.service;

import com.lewisb.bambeuro.entity.User;
import com.lewisb.bambeuro.jpa.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public Optional<User> findById(int userId) {
        LOGGER.info("Finding user with id {}", userId);
        return userRepository.findById(userId);
    }

    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public void saveOrUpdate(User... users) {
        for (User user : users) {
            userRepository.save(user);
            LOGGER.info("Created new user: {}", user);
        }
    }
}
