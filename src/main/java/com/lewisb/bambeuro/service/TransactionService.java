package com.lewisb.bambeuro.service;

import com.lewisb.bambeuro.entity.PaymentTransaction;
import com.lewisb.bambeuro.jpa.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void saveOrUpdate(PaymentTransaction... paymentTransactions) {
        for (PaymentTransaction paymentTransaction : paymentTransactions) {
            transactionRepository.save(paymentTransaction);
        }
    }
}
