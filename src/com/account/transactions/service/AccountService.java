package com.account.transactions.service;


import com.account.transactions.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    LoggedUser loggedUser;

    @Autowired
    MemoryCache memoryCache;

    public AccountInfo createAccount(CurrencyEnum currency) {
        AccountInfo accountInfo = new AccountInfo(loggedUser.getUserId(), currency);
        memoryCache.addAccount(accountInfo);
        return accountInfo;
    }

    public void closeAccount(String accountId) throws AccountException {
        memoryCache.closeAccount(accountId);
    }
}

