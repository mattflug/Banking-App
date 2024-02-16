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

    @PostMapping("/accounts/{account_id}")
    public ResponseEntity<Account> createNewAccount(@PathVariable Account newAccount, @RequestBody Integer userId){
        return ResponseEntity.ok(accountService.createNewAccount(newAccount, userId));
    }

    @PutMapping("/accounts/{account_id}")
    public ResponseEntity<Account> deposit(@PathVariable Integer accountId, @RequestBody BigDecimal amount) {
        return ResponseEntity.ok(accountService.deposit(accountId, amount));
    }

    @PutMapping("/accounts/{account_id}")
    public ResponseEntity<Account> withdraw(@PathVariable Integer accountId, @RequestBody BigDecimal amount) {
        return ResponseEntity.ok(accountService.withdraw(accountId, amount));
    }

//    1. issue with three variables/ 2. used service class name for @PathVariable & @RequestBody instead of model names. not sure if i should have switched them
//    to make the errors go away
    @PutMapping("/accounts/{account_id}")
    public ResponseEntity<Account> transfer(@PathVariable Integer fromAccountId, @RequestBody Integer toAccountId, @RequestBody BigDecimal amount) {
        accountService.transfer(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{user_id}/accounts")
    public ResponseEntity<List<Account>> viewCustomerAccounts(@PathVariable Integer user_id) {
        return ResponseEntity.ok(accountService.getAccountsByAccountHolder(user_id));
    }

    @DeleteMapping("/accounts/{account_id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer accountId) {
        accountService.deleteByAccountId(accountId);
        return ResponseEntity.ok().build();
    }



}
