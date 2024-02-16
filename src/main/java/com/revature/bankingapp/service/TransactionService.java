package com.revature.bankingapp.service;

import com.revature.bankingapp.DAO.TransactionRepository;
import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void createTransaction(Account account, BigDecimal amount,
                                  Transaction.TransactionType transactionType,
                                  BigDecimal newBalance) {
        Transaction transaction = new Transaction();
        transaction.setDate(LocalDateTime.now());
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionType);
        transaction.setBalance(newBalance);
        transactionRepository.save(transaction);

    }
}
