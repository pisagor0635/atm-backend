package com.ab.atmbackend.controller;

import com.ab.atmbackend.model.Account;
import com.ab.atmbackend.services.account.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account/v1")
@Api(tags = "Account operations")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    @ApiOperation(value = "create account - rest api")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @GetMapping("/check/{accountNumber}/{pin}")
    @ApiOperation(value = "check balance - rest api")
    public ResponseEntity<String> checkBalance(@PathVariable String accountNumber, @PathVariable String pin) {
        return ResponseEntity.ok(accountService.checkBalance(accountNumber, pin));
    }

    @PutMapping("/withdrawal/{accountNumber}/{pin}/{withDrawnAmount}")
    @ApiOperation(value = "update account - rest api")
    public ResponseEntity<String> withdrawal(@PathVariable String accountNumber, @PathVariable String pin, @PathVariable String withDrawnAmount) {
        return ResponseEntity.ok(accountService.whitdraw(accountNumber, pin, withDrawnAmount));
    }


}
