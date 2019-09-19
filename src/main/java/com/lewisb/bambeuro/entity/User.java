package com.lewisb.bambeuro.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


//TODO User needs password
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)

    private String name;

    private int bambeuroBalance; // Assume integer bambeuros, subject to change to BigDecimal for decimal values (bambcents?)

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PaymentTransaction> transactions;

    public User() {

    }

    public User(String name) {
        this.name = name;
        bambeuroBalance = 100; // Initial offering of 100
        transactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getBambeuroBalance() {
        return bambeuroBalance;
    }

    public List<PaymentTransaction> getTransactions() {
        return new ArrayList<>(transactions); // Return copy so values can't be altered
    }

    public void addTransaction(PaymentTransaction paymentTransaction) {
        transactions.add(paymentTransaction);
    }

    public void decreaseBalance(int decreaseAmount) {
        bambeuroBalance -= decreaseAmount;
    }

    public void increaseBalance(int increaseAmount) {
        bambeuroBalance += increaseAmount;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
