package com.lewisb.bambeuro.transaction;

import com.lewisb.bambeuro.entity.PaymentTransaction;
import com.lewisb.bambeuro.entity.User;
import com.lewisb.bambeuro.service.TransactionService;
import com.lewisb.bambeuro.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionHandler {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionHandler.class);

    public Optional<PaymentTransaction> handleTransaction(User sender, User recipient, int value) {
        if (sender.getBambeuroBalance() >= value) {
            sender.decreaseBalance(value);
            recipient.increaseBalance(value);
            PaymentTransaction transaction = new PaymentTransaction(sender, recipient, value);
            transactionService.saveOrUpdate(transaction);
            sender.addTransaction(transaction);
            recipient.addTransaction(transaction);
            userService.saveOrUpdate(sender, recipient);
            LOGGER.info("{} Sent {} {} bambeuros", sender, recipient, value);
            return Optional.of(transaction);
        } else {
            LOGGER.warn("User: {} only has {} bambeuros so can't send the required amount: {} bambeuros", sender.getName(), sender.getBambeuroBalance(), value);
            return Optional.empty();
        }
    }

}
