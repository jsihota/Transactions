package com.transactions.model;

import java.util.List;
import java.io.Serializable;

public class UserTransaction implements Serializable {
    private int id;
    private double average;
    private List<Transaction> transactions;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
