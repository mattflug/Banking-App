package com.revature.bankingapp.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "accountNumber", nullable = false, unique = true)
    private String accountNumber;

    public enum accountType {
        YES, NO
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "accountType")
    private accountType accountType;

    @Column(name = "currentBalance")
    private String currentBalance;

//  1. current balance to integer?

// 2. add foriegn key here

    public Account() {

    }

//    3. enum question
//    4. add forien key below
//    5. account type enum squigly
    public Account(Integer id, String accountNumber, enum accountType, String currentBalance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.currentBalance = currentBalance;
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

    public String getaccountType() {
        return accountType;
    }

    //    6. enum question here
    public void setaccountType(enum accountType) {
        this.accountType = accountType;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    //    7. set current balance string again? / 8 . add userid below (have not edited line 85 from Kendra's
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return isAdmin() == user.isAdmin() && Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword());
    }

    //    9. add user id below
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccountNumber(), getaccountType(), getCurrentBalance());
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", currentBalance=" + currentBalance +
                '}';
    }
}
