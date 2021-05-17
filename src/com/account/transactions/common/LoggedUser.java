package com.account.transactions.common;

import org.springframework.stereotype.Service;

@Service
public class LoggedUser {

    private String userId;


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
