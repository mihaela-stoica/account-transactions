package com.account.transactions.common;

public class AccountException extends Exception {
    public enum ExceptionReason {
        ACCOUNT_NOT_EXISTING, ACCOUNT_CLOSED;
    }

    private AccountException.ExceptionReason exceptionReason;

    public AccountException(AccountException.ExceptionReason exceptionReason){
        this.exceptionReason = exceptionReason;
    }

    public AccountException.ExceptionReason getExceptionReason() {
        return exceptionReason;
    }
}
