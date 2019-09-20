package com.lewisb.bambeuro.controller;

import com.lewisb.bambeuro.entity.PaymentTransaction;
import com.lewisb.bambeuro.entity.User;
import com.lewisb.bambeuro.service.UserService;
import com.lewisb.bambeuro.transaction.TransactionDetails;
import com.lewisb.bambeuro.transaction.TransactionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionHandler transactionHandler;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/search/{name}")
    public int getUserIdByName(@PathVariable String name) {
        Optional<User> user = userService.findByName(name);
        if (user.isPresent()) {
            return user.get().getId();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user not found"
            );
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> user = userService.findById(Integer.parseInt(id));
        if (!user.isPresent()) {
            LOGGER.warn("Could not find user with id {}", id);
        }
        return ResponseEntity.of(user);
    }

    @GetMapping("/users/{id}/transactions")
    public ResponseEntity<List<TransactionDetails>> getUserTransactions(@PathVariable String id) {
        Optional<User> user = userService.findById(Integer.parseInt(id));
        List<TransactionDetails> transactionDetails = new ArrayList<>();
        if (!user.isPresent()) {
            LOGGER.warn("Could not find user with id {}", id);
            return new ResponseEntity<>(transactionDetails, HttpStatus.BAD_REQUEST);
        }
        for (PaymentTransaction paymentTransaction : user.get().getTransactions()) {
            transactionDetails.add(new TransactionDetails(user.get(), paymentTransaction));
        }

        return new ResponseEntity<>(transactionDetails, HttpStatus.OK);
    }


    @PostMapping("/users/{id}/transaction")
    public Optional<PaymentTransaction> createTransaction(@PathVariable(value = "id") String userId, @RequestBody Map<String, String> body) {
        LOGGER.info("Received transaction request");
        Optional<User> optionalSender = userService.findById(Integer.parseInt(userId));
        String recipientName = body.get("recipient");
        String valueString = body.get("value"); // TODO split out into sub methods, create constants, new class
        assert (valueString != null);
        Optional<User> optionalRecipient = userService.findByName(recipientName); // TODO this logic can go in transactionHandler
        if (optionalSender.isPresent() && optionalRecipient.isPresent()) {
            User recipient = optionalSender.get();
            User sender = optionalRecipient.get();
            return transactionHandler.handleTransaction(recipient, sender, Integer.parseInt(valueString));
        } else {
            LOGGER.warn("Either sender {} or receiver {} not present", optionalSender, optionalRecipient);
            return Optional.empty();
        }
    }

}
