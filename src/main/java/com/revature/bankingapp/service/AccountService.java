package com.revature.bankingapp.service;

import com.revature.bankingapp.DAO.AccountRepository;
import com.revature.bankingapp.DAO.TransactionRepository;
import com.revature.bankingapp.DAO.UserRepository;
import com.revature.bankingapp.exception.CannotDeleteAccountException;
import com.revature.bankingapp.exception.InsufficientFundsException;
import com.revature.bankingapp.model.Account;
import com.revature.bankingapp.model.Transaction;
import com.revature.bankingapp.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;


    public AccountService(AccountRepository accountRepository, UserRepository userRepository,
                          TransactionRepository transactionRepository,
                          TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
    }

    public static String generateAccountNumber() {
        long randomNumber = ThreadLocalRandom.current().nextLong((long) Math.pow(10, 10 - 1), (long) Math.pow(10, 10));
        return Long.toString(randomNumber);
    }

    public Account createNewAccount(Integer userId, Account newAccount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        newAccount.setAccountHolder(user);
        String newAccountNumber = generateAccountNumber();
        while (accountRepository.findByAccountNumber(newAccountNumber).isPresent()) {
            newAccountNumber = generateAccountNumber();
        }
        newAccount.setAccountNumber(newAccountNumber);
        return accountRepository.save(newAccount);
    }

    @Transactional
    public Account deposit(Integer accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new EntityNotFoundException("Account not found"));
        BigDecimal newBalance = account.getCurrentBalance().add(amount)
                        .setScale(2, RoundingMode.HALF_UP);
        account.setCurrentBalance(newBalance);
        accountRepository.save(account);
        transactionService.createTransaction(account, amount,
                Transaction.TransactionType.DEPOSIT, newBalance);
        return account;
    }

    @Transactional
    public Account withdraw(Integer accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new EntityNotFoundException("Account not found"));

        if (account.getCurrentBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        BigDecimal newBalance = account.getCurrentBalance().subtract(amount)
                .setScale(2, RoundingMode.HALF_UP);
        account.setCurrentBalance(newBalance);
        accountRepository.save(account);
        transactionService.createTransaction(account, amount,
                Transaction.TransactionType.WITHDRAWAL, newBalance);
        return account;
    }

    @Transactional
    public void transfer(Integer fromAccountId, Integer toAccountId, BigDecimal amount) {
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(() ->
                new EntityNotFoundException("Transfer from account not found"));
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow(() ->
                new EntityNotFoundException("Transfer to account not found"));

        if (fromAccount.getCurrentBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds for transfer");
        }

        BigDecimal reducedBalance = fromAccount.getCurrentBalance().subtract(amount)
                .setScale(2, RoundingMode.HALF_UP);
        fromAccount.setCurrentBalance(reducedBalance);

        BigDecimal increasedBalance = toAccount.getCurrentBalance().add(amount)
                .setScale(2, RoundingMode.HALF_UP);
        toAccount.setCurrentBalance(increasedBalance);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        transactionService.createTransaction(fromAccount, amount,
                Transaction.TransactionType.TRANSFER_OUTGOING, reducedBalance);
        transactionService.createTransaction(toAccount, amount,
                Transaction.TransactionType.TRANSFER_INCOMING, increasedBalance);

    }

    public List<Account> getAccountsByAccountHolder(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        return accountRepository.findAllByAccountHolder(user);
    }

    @Transactional
    public void deleteByAccountId(Integer accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new EntityNotFoundException("Account not found"));
        if (account.getCurrentBalance().compareTo(BigDecimal.ZERO) == 0) {
            List<Transaction> transactions = transactionRepository.findByAccount(account);
            transactionRepository.deleteAll(transactions);
            accountRepository.deleteById(accountId);
        } else {
            throw new CannotDeleteAccountException("Account balance must be zero before deletion");
            }
        }

    // Method not currently using
//    public List<Account> getAllAccounts() {
//        return accountRepository.findAll();
//    }




    }



