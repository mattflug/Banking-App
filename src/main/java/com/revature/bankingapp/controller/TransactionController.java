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

    @GetMapping("/transactions/{account_id}")
    public ResponseEntity<List<Transaction>> getAccountTransactionsByDate(@PathVariable Integer account_id, @RequestParam String fromDateStr, @RequestParam String toDateStr) {
        transactionService.getAccountTransactionsByDate(account_id, fromDateStr, toDateStr );
        return ResponseEntity.ok(transactionService.getAccountTransactionsByDate);
    }

}
