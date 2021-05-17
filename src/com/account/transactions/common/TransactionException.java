package com.account.transactions.common;

public class TransactionException extends Exception {
    public enum ExceptionReason {
        NOT_PARSABLE;
    }

    private ExceptionReason exceptionReason;

    public TransactionException(ExceptionReason exceptionReason){
        this.exceptionReason = exceptionReason;
    }

    public ExceptionReason getExceptionReason() {
        return exceptionReason;
    }
}

