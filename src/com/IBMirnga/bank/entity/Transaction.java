package com.IBMirnga.bank.entity;

import java.time.LocalDate;

public class Transaction {
    private LocalDate transactionDate;
    private String transactionUSerId;
    private Double transactionAmount;
    private String transactionType;
    private Double initialBalance;
    private Double finalBalance;

    public Transaction(LocalDate transactionDate, String transactionUSerId, Double transactionAmount, String transactionType, Double initialBalance, Double finalBalance) {
        this.transactionDate = transactionDate;
        this.transactionUSerId = transactionUSerId;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionUSerId() {
        return transactionUSerId;
    }

    public void setTransactionUSerId(String transactionUSerId) {
        this.transactionUSerId = transactionUSerId;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(Double finalBalance) {
        this.finalBalance = finalBalance;
    }
}
