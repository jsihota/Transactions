package com.transactions.model;

public class Transaction {
    private String month;
    private double amount;
    private String categorization;
    private String rawMerchant;
    private TransactionAction transactionAction;


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double spent) {
        this.amount = spent;
    }
    public String getCategorization() {
        return categorization;
    }

    public void setCategorization(String categorization) {
        this.categorization = categorization;
    }

    public String getRawMerchant() {
        return rawMerchant;
    }

    public void setRawMerchant(String rawMerchant) {
        this.rawMerchant = rawMerchant;
    }


    public TransactionAction getTransactionAction() {
        return transactionAction;
    }

    public void setTransactionAction(TransactionAction transactionAction) {
        this.transactionAction = transactionAction;
    }
}
