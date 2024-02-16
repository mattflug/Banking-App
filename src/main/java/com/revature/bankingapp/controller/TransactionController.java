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

    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<List<Transaction>> getAllAccountTransactions(@PathVariable Integer accountId) {
        return ResponseEntity.ok(transactionService.getAllAccountTransactions(accountId));
    }

    @GetMapping("/transactions/{date}")
    public ResponseEntity<Account> getAccountTransactionsByDate(@PathVariable Integer accountId, @PathVariable String fromDateStr, @PathVariable String toDateStr) {
        transactionService.getAccountTransactionsByDate(accountId, fromDateStr, toDateStr );
        return ResponseEntity.ok().build();
    }

}
