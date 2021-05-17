package com.account.transactions.service;

import com.account.transactions.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

import static com.account.transactions.common.TransactionException.ExceptionReason.NOT_PARSABLE;

@Service
public class TransactionService {
    private Logger log = Logger.getLogger(TransactionService.class.getName());
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    private MemoryCache memoryCache;

    @Autowired
    private AccountService accountService;


    public TransactionInfo createTransaction(String accountId, TransactionRequest transactionRequest) throws TransactionException, AccountException {
        validateInput(transactionRequest);

        TransactionInfo transactionInfo = new TransactionInfo(accountId, LocalDateTime.now(), transactionRequest.getAmount(),
                transactionRequest.getTransactionType());

        AccountInfo accountInfo = memoryCache.getAccount(accountId);
        if(accountInfo.getStatus() == AccountStatus.CLOSED){
            log.warning("Not possible to add transactions to closed account "+ accountId);
            throw new AccountException(AccountException.ExceptionReason.ACCOUNT_CLOSED);
        }

        memoryCache.addTransaction(transactionInfo);
        log.info("Transaction created for account " + accountId);

        return transactionInfo;

    }


    public List<TransactionInfo> getTransactions (String accountId, String fromDate, String toDate) throws AccountException {
        String start = fromDate.replaceAll("\"", "");
        String end = toDate.replaceAll("\"", "");

        LocalDateTime fromDateParsed = LocalDateTime.parse(start, formatter);
        LocalDateTime toDateParsed = LocalDateTime.parse(end, formatter);

        return memoryCache.getTransactionInfoList(accountId, fromDateParsed, toDateParsed);

    }



    private void validateInput(TransactionRequest transactionRequest) throws TransactionException {

        try {
            Double.parseDouble(transactionRequest.getAmount());
        } catch (Exception e) {
            log.warning("Not valid input");
            throw new TransactionException(NOT_PARSABLE);
        }
    }

}
