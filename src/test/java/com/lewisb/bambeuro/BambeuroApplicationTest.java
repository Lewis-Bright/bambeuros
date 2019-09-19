package com.lewisb.bambeuro;

import com.lewisb.bambeuro.controller.LoginController;
import com.lewisb.bambeuro.controller.UserController;
import com.lewisb.bambeuro.entity.User;
import com.lewisb.bambeuro.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BambeuroApplicationTest {

    @Autowired
    private UserController userController;

    @Autowired
    private LoginController loginController;

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    void testTransferBambeuros() {
        loginController.createUser(Map.of("name", "testUser1"));
        loginController.createUser(Map.of("name", "testUser2"));
        Optional<User> testUser1 = userService.findByName("testUser1");
        assertThat(testUser1).isNotEmpty();
        assertThat(testUser1.get().getBambeuroBalance()).as("All new users start with 100 bambeuros").isEqualTo(100);
        Map<String, String> transactionBody = Map.of("recipient", "testUser2", "value", "40");
        userController.createTransaction(testUser1.get().getId(), transactionBody);
        testUser1 = userService.findByName("testUser1");
        assertThat(testUser1).isNotEmpty();
        assertThat(testUser1.get().getBambeuroBalance()).as("user 1 transferred 40 of 100, leaving 60").isEqualTo(60);
        Optional<User> testUser2 = userService.findByName("testUser2");
        assertThat(testUser2).isNotEmpty();
        assertThat(testUser2.get().getBambeuroBalance()).as("user 2 was transferred 40, so should have total of 160").isEqualTo(140);
    }


}