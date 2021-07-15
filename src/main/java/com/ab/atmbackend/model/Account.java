package com.ab.atmbackend.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_number",unique = true)
    private String accountNumber;

    @Column(name = "pin")
    private String pin;

    @Column(name = "opening_balance")
    private String openingBalance;

    @Column(name = "balance")
    @ApiModelProperty(hidden = true)
    private String balance;

    @Column(name = "overdraft")
    private String overdraft;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    public Account() {
    }

    public Account(String accountNumber, String pin, String openingBalance, String overdraft) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.openingBalance = openingBalance;
        this.overdraft = overdraft;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(String overdraft) {
        this.overdraft = overdraft;
    }
}
