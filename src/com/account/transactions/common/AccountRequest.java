package com.account.transactions.common;

public class AccountRequest {
    private CurrencyEnum currency;

    public AccountRequest(CurrencyEnum currency) {
        this.currency = currency;
    }


    public AccountRequest(){

    }

    public CurrencyEnum getCurrency() {
        return currency;
    }
}
