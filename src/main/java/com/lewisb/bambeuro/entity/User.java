package com.lewisb.bambeuro.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int bambeuroBalance; // Assume integer bambeuros, subject to change for decimal values (bambcents?)

//    private List<Transaction> transactions;

    public User() {

    }

    public User(String name) {
        this.name = name;
        bambeuroBalance = 100; // Initial offering of 100
//        transactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getBambeuroBalance() {
        return bambeuroBalance;
    }

//    public List<Transaction> getTransactions() {
//        return transactions;
//    }
}
