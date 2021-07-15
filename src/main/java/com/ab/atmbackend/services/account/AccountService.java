package com.ab.atmbackend.services.account;

import com.ab.atmbackend.model.Account;

import java.util.List;

public interface AccountService {

    Account createAccount(Account account);

    List<Account> getAllAccounts();

    String checkBalance(String accountNumber, String pin);

    String whitdraw(String accountNumber, String pin, String withDrawnAmount);
}
