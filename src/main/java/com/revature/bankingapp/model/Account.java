package com.revature.bankingapp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer id;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    public enum AccountType {
        CHECKING, SAVINGS
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "current_balance", precision = 13, scale = 4)
    private BigDecimal currentBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User accountHolder;


    public Account() {

    }

    public Account(Integer id, String accountNumber, AccountType accountType, BigDecimal currentBalance, User accountHolder) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.currentBalance = currentBalance;
        this.accountHolder = accountHolder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public User getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(User accountHolder) {
        this.accountHolder = accountHolder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(getId(), account.getId()) && Objects.equals(getAccountNumber(), account.getAccountNumber()) && getAccountType() == account.getAccountType() && Objects.equals(getCurrentBalance(), account.getCurrentBalance()) && Objects.equals(getAccountHolder(), account.getAccountHolder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccountNumber(), getAccountType(), getCurrentBalance(), getAccountHolder());
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType=" + accountType +
                ", currentBalance=" + currentBalance +
                ", accountHolder=" + accountHolder +
                '}';
    }
}
