package com.ab.atmbackend.services.account.impl;

import com.ab.atmbackend.model.Account;
import com.ab.atmbackend.repository.AccountRepository;
import com.ab.atmbackend.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private Environment env;

    @Autowired
    private AccountRepository accountRepository;

    @Value("maximum_withdrawal_amount")
    private String maximum_withdrawal_amount;

    private Account account;

    @Override
    public Account createAccount(Account account) {
        account.setBalance(account.getOpeningBalance());
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public String checkBalance(String accountNumber, String pin) {

        StringBuilder message = new StringBuilder();

        if (verifyUser(accountNumber, pin) && account != null) {
            message.append("There are ").append(account.getBalance()).append(" Euro in your acount.");
            if (account.getBalance() > Integer.parseInt(env.getProperty(maximum_withdrawal_amount))) {
                message.append(" You can get maximum : ").append(env.getProperty(maximum_withdrawal_amount)).append(" Euro a day!");
            }
        } else {
            message.append("Check account number or pin!");
        }
        return message.toString();
    }

    @Override
    public String whitdraw(String accountNumber, String pin, String withDrawnAmount) {

        String message = "";

        if (verifyUser(accountNumber, pin) && account != null) {
            message = manageWithDraw(withDrawnAmount);
        }

        return message;
    }

    @Transactional
    public String manageWithDraw(String withDrawnAmount) {

        StringBuilder message = new StringBuilder();

        if (Integer.parseInt(withDrawnAmount) % 5 != 0) {
            message.append("You can withdraw 5 Euros and its multiples!");
        } else {
            if (Integer.parseInt(withDrawnAmount) > Integer.parseInt(env.getProperty(maximum_withdrawal_amount))) {
                message.append("You cannot exceed the daily withdrawal limit! The daily limit is :").append(env.getProperty(maximum_withdrawal_amount)).append(" Euros!");
            } else if (Integer.parseInt(withDrawnAmount) > (account.getBalance() + account.getOverdraft())) {
                message.append("Insufficient balance! Your available limit is : ").append(account.getBalance() + account.getOverdraft()).append(" Euros.");
            } else if (Integer.parseInt(withDrawnAmount) <= account.getBalance() + account.getOverdraft()) {
                message.append("You withdraw ").append(withDrawnAmount).append(" Euros.").append(" Your remaining money is : ").append(account.getBalance() - Integer.parseInt(withDrawnAmount)).append(" Euros.").append("\n");

                message.append(classifyMoneys(withDrawnAmount));

                int availableBalance = account.getBalance() - Integer.parseInt(withDrawnAmount);

                account.setBalance(availableBalance);
                accountRepository.save(account);
            }
        }

        return message.toString();
    }

    private String classifyMoneys(String withDrawnAmount) {

        StringBuilder moneyClassification = new StringBuilder();

        int money = Integer.parseInt(withDrawnAmount);

        moneyClassification.append("ATM gives : ");

        if (money >= 50) {
            moneyClassification.append(money / 50).append(", 50 Euros ");
            money = money % 50;
        }

        if (money >= 20) {
            moneyClassification.append(money / 20).append(", 20 Euros ");
            money = money % 20;
        }

        if (money >= 10) {
            moneyClassification.append(money / 10).append(", 10 Euros ");
            money = money % 10;
        }

        if (money == 5) {
            moneyClassification.append("1, 5 Euros ");
        }

        return moneyClassification.toString();

    }

    private boolean verifyUser(String accountNumber, String pin) {

        account = accountRepository.findAccountByAccountNumber(accountNumber);
        if (account != null && account.getPin().equals(pin)) {
            return true;
        } else {
            return false;
        }
    }
}
