package com.lewisb.bambeuro.entity;

public interface Transaction {

    int getValue();

    User getRecipient();

    User getSender();
}
