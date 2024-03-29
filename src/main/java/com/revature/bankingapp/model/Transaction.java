package com.revature.bankingapp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer id;


    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER_OUTGOING, TRANSFER_INCOMING
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "amount", nullable = false, precision = 13, scale = 4)
    private BigDecimal amount;

    @Column(name = "balance", precision = 13, scale = 4)
    private BigDecimal balance;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "account_id")
    private Account account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance.setScale(2, RoundingMode.HALF_UP);
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(id, that.id) && transactionType == that.transactionType && Objects.equals(date, that.date) && Objects.equals(amount, that.amount) && Objects.equals(balance, that.balance) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionType, date, amount, balance, account);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionType=" + transactionType +
                ", date=" + date +
                ", amount=" + amount +
                ", balance=" + balance +
                ", account=" + account +
                '}';
    }
}



