package com.account.transactions.common;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class MemoryCache {
    private Logger log = Logger.getLogger(MemoryCache.class.getName());

    private static Map<AccountInfo, List<TransactionInfo>> accountTransactionMap = new HashMap<>();

    public synchronized void addTransaction(TransactionInfo transactionInfo) throws AccountException {
        String accountId = transactionInfo.getAccountId();
        Map.Entry<AccountInfo, List<TransactionInfo>>  accountInfo = getAccountInfoEntry(accountId);
        accountInfo.getValue().add(transactionInfo);
    }

    public synchronized void addAccount(AccountInfo accountInfo){
        accountTransactionMap.put(accountInfo, new ArrayList<>());
    }


    public synchronized List<TransactionInfo> getTransactionInfoList(String accountId, LocalDateTime fromDate, LocalDateTime toDate) throws AccountException {

        Map.Entry<AccountInfo, List<TransactionInfo>>  accountInfo = getAccountInfoEntry(accountId);
        return accountInfo.getValue().stream().filter(transactionInfo ->
               transactionInfo.getTimestamp().isAfter(fromDate) && transactionInfo.getTimestamp().isBefore(toDate)
       ).collect(Collectors.toList());

    }

    private Map.Entry<AccountInfo, List<TransactionInfo>> getAccountInfoEntry (String accountId) throws AccountException {
        Optional<Map.Entry<AccountInfo, List<TransactionInfo>>> optional = accountTransactionMap.entrySet().stream()
                .filter(entry -> entry.getKey().getAccountId().equals(accountId))
                .findAny();

        if (!optional.isPresent()) {
            log.warning("Not found account id "+ accountId);
            throw new AccountException(AccountException.ExceptionReason.ACCOUNT_NOT_EXISTING);
        }

        return optional.get();
    }

    public AccountInfo getAccount(String accountId) throws AccountException {
        return getAccountInfoEntry(accountId).getKey();
    }

    public synchronized void closeAccount(String accountId) throws AccountException {
        Map.Entry<AccountInfo, List<TransactionInfo>>  accountInfo = getAccountInfoEntry(accountId);
        accountInfo.getKey().setStatus(AccountStatus.CLOSED);
        log.info("Account closed " + accountId);
    }

}
