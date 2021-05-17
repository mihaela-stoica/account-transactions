package com.account.transactions.test;

import com.account.transactions.common.*;
import com.account.transactions.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    @Mock
    MemoryCache memoryCache;


    @Mock
    LoggedUser loggedUser;

    @Test(expected = AccountException.class)
    public void transactions_cant_be_added_to_closed_accounts() throws AccountException, TransactionException {

        AccountInfo accountInfo = new AccountInfo(loggedUser.getUserId(), CurrencyEnum.EUR);
        accountInfo.setStatus(AccountStatus.CLOSED);

        when(loggedUser.getUserId()).thenReturn("userX");
        when(memoryCache.getAccount("accountId")).thenReturn(accountInfo);


        TransactionRequest transactionRequest = new TransactionRequest("9", TransactionType.DEPOSIT);
        transactionService.createTransaction("accountId", transactionRequest);

    }
}
