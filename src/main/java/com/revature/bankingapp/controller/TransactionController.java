package com.revature.bankingapp.controller;

import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Transaction;
import com.revature.bankingapp.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllAccountTransactions() {
        List<Account> getAllAccountTransactions = transactionService.getAllAccountTransactions();
        return ResponseEntity.ok(transactionService.getAllAccountTransactions);
    }

    @GetMapping("/transactions/{date}")
    public ResponseEntity<Account> getAccountTransactionsByDate(@PathVariable LocalDateTime date) {
        return ResponseEntity.ok(transactionService.getAccountTransactionsByDate(date));
    }

}
