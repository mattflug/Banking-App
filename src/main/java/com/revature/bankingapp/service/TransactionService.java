package com.revature.bankingapp.service;

import com.revature.bankingapp.DAO.AccountRepository;
import com.revature.bankingapp.DAO.TransactionRepository;
import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Transaction;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public List<Transaction> getAllAccountTransactions(Integer accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        return transactionRepository.findAllByAccount(account);

    }


    public List<Transaction> getAccountTransactionsByDate(Integer accountId, String fromDateStr, String toDateStr) {
       accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
        LocalDate fromDate = LocalDate.parse(fromDateStr);
        LocalDate toDate = LocalDate.parse(toDateStr);

        if (fromDate.isAfter(toDate)) {
            throw new IllegalArgumentException("From date must be before To date");
        }
        LocalDateTime fromDateTime = fromDate.atStartOfDay();
        LocalDateTime toDateTime = toDate.atTime(23, 59, 59, 999999999);

        return transactionRepository.findByAccountIdAndDateBetween(accountId, fromDateTime, toDateTime);

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
