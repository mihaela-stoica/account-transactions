package com.account.transactions.common;

import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScope
public class TransactionInfo {

    private String id;
    private String amount;
    private LocalDateTime timestamp;
    private String accountId;
    private TransactionType transactionType;


    public TransactionInfo(String accountId, LocalDateTime timestamp, String amount, TransactionType transactionType) {
        this.id = UUID.randomUUID().toString();
        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
    }

    public String getAmount() {
        return amount;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public String getAccountId() { return accountId; }
    public TransactionType getTransactionType() { return transactionType; }
}

