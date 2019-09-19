package com.lewisb.bambeuro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class PaymentTransaction implements Transaction {

    @Id
    @GeneratedValue
    private int id;

    @JsonIgnore // Infinite recursion if this includes user, as that includes this and etc
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    private int value;

    public PaymentTransaction() {
    }

    public PaymentTransaction(User sender, User recipient, int value) {
        this.recipient = recipient;
        this.sender = sender;
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public User getRecipient() {
        return recipient;
    }

    @Override
    public User getSender() {
        return sender;
    }
}
