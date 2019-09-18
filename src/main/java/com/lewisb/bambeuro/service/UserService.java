package com.lewisb.bambeuro.service;

import com.lewisb.bambeuro.entity.User;
import com.lewisb.bambeuro.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }
}
