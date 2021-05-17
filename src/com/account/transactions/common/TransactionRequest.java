package com.account.transactions.common;

public class TransactionRequest {
    private String amount;
    private TransactionType transactionType;


    public TransactionRequest(String amount, TransactionType transactionType)
    {
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public TransactionRequest(){}

    public String getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }
}
