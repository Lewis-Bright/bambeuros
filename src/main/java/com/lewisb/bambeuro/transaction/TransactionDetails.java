package com.lewisb.bambeuro.transaction;

import com.lewisb.bambeuro.entity.Transaction;
import com.lewisb.bambeuro.entity.User;

/**
 * Used to display information to the viewer about the current transaction. Needs knowledge of view to know if transaction is debit or credit
 */
public final class TransactionDetails {

    private final DebitCreditType debitCreditType;

    private final int bambeuroValue; // Always positive, sign is derived from credit debit type

    private final String recipient;

    public TransactionDetails(User viewer, Transaction transaction) {
        this.recipient = transaction.getRecipient().getName();
        debitCreditType = viewer == transaction.getRecipient() ? DebitCreditType.CREDIT : DebitCreditType.DEBIT;
        bambeuroValue = transaction.getValue();
    }

    public DebitCreditType getDebitCreditType() {
        return debitCreditType;
    }

    public int getBambeuroValue() {
        return bambeuroValue;
    }

    public String getRecipient() {
        return recipient;
    }
}
