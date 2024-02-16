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
    public ResponseEntity<List<Transaction>> viewAllTransactions() {
        List<Account> viewAllTransactions = transactionService.viewAllTransactions();
        return ResponseEntity.ok(transactionService.viewAllTransactions);
    }

    @PostMapping("/transactions/{transaction_id}")
    public ResponseEntity<Account> addTransaction(@PathVariable Integer transaction_id, @RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.addTransaction(transaction_id, transaction));
    }

    @GetMapping("/transactions/{date}")
    public ResponseEntity<Account> viewTransactionsByDate(@PathVariable LocalDateTime date) {
        return ResponseEntity.ok(transactionService.viewTransactionsByDate(date));
    }

}
