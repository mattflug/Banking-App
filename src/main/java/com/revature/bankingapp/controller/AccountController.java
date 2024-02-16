package com.revature.bankingapp.controller;

import com.revature.bankingapp.service.AccountService;
import com.revature.bankingapp.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts/{account_id}")
    public ResponseEntity<Account> createNewAccount(@PathVariable Integer account_id, @RequestBody Account account){
        return ResponseEntity.ok(accountService.createNewAccount(account_id, account));
    }

@GetMapping("/accounts")
public ResponseEntity<List<Account>> getAllAccount() {
    List<Account> getAllAccount = accountService.getAllAccount();
    return ResponseEntity.ok(accountService.getAllAccount);
}

    @PutMapping("/accounts/{account_id}")
    public ResponseEntity<Account> deposit(@PathVariable Integer account_id, @RequestBody Account account) {
        return ResponseEntity.ok(accountService.deposit(account_id, account));
    }

    @PutMapping("/accounts/{account_id}")
    public ResponseEntity<Account> withdraw(@PathVariable Integer account_id, @RequestBody Account account) {
        return ResponseEntity.ok(accountService.withdraw(account_id, account));
    }

    @PutMapping("/accounts/{account_id}")
    public ResponseEntity<Account> transfer(@PathVariable Integer account_id, @RequestBody Account account) {
        return ResponseEntity.ok(accountService.transfer(account_id, account));
    }

    @DeleteMapping("/accounts/{account_id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer account_id) {
        accountService.deleteById(account_id);
        return ResponseEntity.ok().build();
    }







}
