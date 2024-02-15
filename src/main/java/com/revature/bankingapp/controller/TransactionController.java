package com.revature.bankingapp.controller;

import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Transaction;
import com.revature.bankingapp.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    //    2. work on get all transactions
    @GetMapping("todos/{toDoId}")
    public ResponseEntity<Account> getToDoById(@PathVariable Integer toDoId) {
        return ResponseEntity.ok(toDoService.getToDoById(toDoId));
    }

    //    3. work on get all transactions by date
    @GetMapping("todos/{toDoId}")
    public ResponseEntity<Account> getToDoById(@PathVariable Integer toDoId) {
        return ResponseEntity.ok(toDoService.getToDoById(toDoId));
    }

}
