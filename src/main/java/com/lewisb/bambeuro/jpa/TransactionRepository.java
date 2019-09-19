package com.lewisb.bambeuro.jpa;

import com.lewisb.bambeuro.entity.PaymentTransaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<PaymentTransaction, Integer> {
}
