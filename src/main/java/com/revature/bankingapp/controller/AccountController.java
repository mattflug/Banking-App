package com.revature.bankingapp.controller;

import com.revature.bankingapp.DTO.DepositOrWithdrawalRequest;
import com.revature.bankingapp.DTO.TransferRequest;
import com.revature.bankingapp.service.AccountService;
import com.revature.bankingapp.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts/{user_id}")
    public ResponseEntity<Account> createNewAccount(@PathVariable Integer user_id, @RequestBody Account newAccount){
        return ResponseEntity.ok(accountService.createNewAccount(user_id, newAccount));
    }

    @PutMapping("/accounts/{account_id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Integer account_id,  @RequestBody DepositOrWithdrawalRequest dto) {
        return ResponseEntity.ok(accountService.deposit(account_id, dto.getAmount()));
    }

    @PutMapping("/accounts/{account_id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Integer account_id, @RequestBody DepositOrWithdrawalRequest dto) {
        return ResponseEntity.ok(accountService.withdraw(account_id, dto.getAmount()));
    }
    
    @PutMapping("/accounts/{account_id}/transfer")
    public ResponseEntity<Account> transfer(@PathVariable Integer account_id, @RequestBody TransferRequest dto) {
        accountService.transfer(account_id, dto.getToAccountId(), dto.getAmount());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{user_id}/accounts")
    public ResponseEntity<List<Account>> viewCustomerAccounts(@PathVariable Integer user_id) {
        return ResponseEntity.ok(accountService.getAccountsByAccountHolder(user_id));
    }

    @DeleteMapping("/accounts/{account_id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer account_id) {
        accountService.deleteByAccountId(account_id);
        return ResponseEntity.ok().build();
    }



}
