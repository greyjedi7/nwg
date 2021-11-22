package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaction {

    @Id
    private int transactionId;
    private String counterParty;
    private String party;
    private Double transactionAmount;
    private Double remainingBalance;

    public Transaction() {
    }

    public Transaction(int transactionId, String counterParty, String party, Double transactionAmount, Double remainingBalance) {
        this.transactionId = transactionId;
        this.counterParty = counterParty;
        this.party = party;
        this.transactionAmount = transactionAmount;
        this.remainingBalance = remainingBalance;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getCounterParty() {
        return counterParty;
    }

    public void setCounterParty(String counterParty) {
        this.counterParty = counterParty;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Double getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(Double remainingBalance) {
        this.remainingBalance = remainingBalance;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", counterParty='" + counterParty + '\'' +
                ", party='" + party + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", remainingBalance=" + remainingBalance +
                '}';
    }
}
