package com.lewisb.bambeuro.transaction;

import com.lewisb.bambeuro.entity.PaymentTransaction;
import com.lewisb.bambeuro.entity.User;
import com.lewisb.bambeuro.service.TransactionService;
import com.lewisb.bambeuro.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionHandlerTest {

    private static final int VALUE = 10;

    @InjectMocks
    private TransactionHandler transactionHandler;

    @Mock
    private TransactionService transactionService;

    @Mock
    private UserService userService;

    @Mock
    private User sender;

    @Mock
    private User recipient;

    @Test
    void handleTransactionNoMoney() {
        // Given
        when(sender.getBambeuroBalance()).thenReturn(0); // sender has no money
        // When
        Optional<PaymentTransaction> resultingTransaction = transactionHandler.handleTransaction(sender, recipient, VALUE);
        // Then
        assertThat(resultingTransaction).as("Sender has no money so no transaction should take place").isEmpty();
    }

    @Test
    void handleTransactionEnoughMoney() {
        // Given
        when(sender.getBambeuroBalance()).thenReturn(30); // sender has enough money
        // When
        Optional<PaymentTransaction> resultingTransaction = transactionHandler.handleTransaction(sender, recipient, VALUE);
        // Then

        PaymentTransaction expectedTransaction = new PaymentTransaction(sender, recipient, VALUE);
        assertThat(resultingTransaction).isNotEmpty();
        assertThat(resultingTransaction.get()).isEqualTo(expectedTransaction);
    }
}