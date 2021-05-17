package com.account.transactions.controller;

import com.account.transactions.common.AccountException;
import com.account.transactions.common.AccountInfo;
import com.account.transactions.common.AccountRequest;
import com.account.transactions.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;


    @PostMapping("/account")
    public ResponseEntity createAccount(@RequestBody AccountRequest accountRequest){
        AccountInfo accountInfo = accountService.createAccount(accountRequest.getCurrency());
        return new ResponseEntity(accountInfo,HttpStatus.CREATED);
    }

    @PutMapping("/account/{accountId}")
    public ResponseEntity closeAccount(@PathVariable("accountId") String accountId) {
        try {
            accountService.closeAccount(accountId);

        } catch (AccountException e) {
            switch (e.getExceptionReason()) {
                case ACCOUNT_NOT_EXISTING: {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

                }
            }

        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
