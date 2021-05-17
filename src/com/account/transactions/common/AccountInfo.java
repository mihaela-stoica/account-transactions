package com.account.transactions.common;

import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@ApplicationScope
public class AccountInfo {
    private String accountId;
    private String userId;
    private CurrencyEnum currency;
    private AccountStatus status;
    private LocalDateTime createdAt;

    public AccountInfo(String userId, CurrencyEnum currency) {
        this.accountId = UUID.randomUUID().toString();
        this.userId = userId;
        this.currency = currency;
        this.status = AccountStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }


    public String getUserId() {
        return userId;
    }


    public String getAccountId() {
        return accountId;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public AccountStatus getStatus(){
        return status;
    }

    public void setStatus(AccountStatus status){
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountInfo that = (AccountInfo) o;
        return Objects.equals(accountId, that.accountId) &&
                Objects.equals(userId, that.userId) &&
                currency == that.currency &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, userId, currency, status);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
