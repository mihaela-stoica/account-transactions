package com.account.transactions.test;

import com.account.transactions.common.*;
import com.account.transactions.service.AccountService;
import com.account.transactions.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    LoggedUser loggedUser;


    @Test()
    public void account_is_created_with_correct_status_user_and_timestamp() throws AccountException, TransactionException {

        when(loggedUser.getUserId()).thenReturn("userX");

        AccountInfo accountInfo = accountService.createAccount(CurrencyEnum.EUR);


        assertNotNull(accountInfo.getAccountId());
        assertNotNull(accountInfo.getCreatedAt());
        assert(accountInfo.getStatus()).equals(AccountStatus.ACTIVE);
        assert(accountInfo.getUserId()).equals("userX");
        assert(accountInfo.getCurrency()).equals(CurrencyEnum.EUR);


    }

}
