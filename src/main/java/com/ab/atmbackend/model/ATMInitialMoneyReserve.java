package com.ab.atmbackend.model;

public class ATMInitialMoneyReserve {
    private int value;
    private int amount;

    public ATMInitialMoneyReserve(int value, int amount) {
        this.value = value;
        this.amount = amount;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
