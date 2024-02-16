package com.revature.bankingapp.controller;

import com.revature.bankingapp.service.AccountService;
import com.revature.bankingapp.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts/{user_id}")
    public ResponseEntity<Account> createNewAccount(@RequestBody Account newAccount, @PathVariable Integer user_id){
        return ResponseEntity.ok(accountService.createNewAccount(newAccount, user_id));
    }

    @PutMapping("/accounts/{account_id}")
    public ResponseEntity<Account> deposit(@PathVariable Integer account_id, @RequestBody BigDecimal amount) {
        return ResponseEntity.ok(accountService.deposit(account_id, amount));
    }

    @PutMapping("/accounts/{account_id}")
    public ResponseEntity<Account> withdraw(@PathVariable Integer account_id, @RequestBody BigDecimal amount) {
        return ResponseEntity.ok(accountService.withdraw(account_id, amount));
    }
    
    @PutMapping("/accounts/{account_id}")
    public ResponseEntity<Account> transfer(@PathVariable Integer fromAccountId, @RequestBody Integer toAccountId, @RequestBody BigDecimal amount) {
        accountService.transfer(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/accounts/{account_id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer account_id) {
        accountService.deleteByAccountId(account_id);
        return ResponseEntity.ok().build();
    }







}
