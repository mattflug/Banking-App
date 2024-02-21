package com.revature.bankingapp.DTO;

import java.math.BigDecimal;

public class TransferRequest {
    private Integer toAccountId;
    private BigDecimal amount;

    public Integer getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Integer toAccountId) {
        this.toAccountId = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
