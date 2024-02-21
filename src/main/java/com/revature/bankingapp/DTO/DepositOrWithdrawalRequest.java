package com.revature.bankingapp.DTO;

import java.math.BigDecimal;

public class DepositOrWithdrawalRequest {


    private BigDecimal amount;


    public DepositOrWithdrawalRequest() {
    }

    public DepositOrWithdrawalRequest(BigDecimal amount) {
        this.amount = amount;
    }

        public BigDecimal getAmount() {
        return amount;
    }

        public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
