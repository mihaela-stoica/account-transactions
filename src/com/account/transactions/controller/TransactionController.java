package com.account.transactions.controller;


import com.account.transactions.common.AccountException;
import com.account.transactions.common.TransactionException;
import com.account.transactions.common.TransactionInfo;
import com.account.transactions.common.TransactionRequest;
import com.account.transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @PostMapping("/account/{accountId}/transaction")
    public ResponseEntity createTransaction(@PathVariable ("accountId") String accountId,
                                            @RequestBody TransactionRequest transactionRequest){
        TransactionInfo transactionInfo = null;
        try {
            transactionInfo =  transactionService.createTransaction(accountId, transactionRequest);
        }catch(TransactionException e){
            switch (e.getExceptionReason()){
                case NOT_PARSABLE:{
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
                }
                default:
                    break;
            }

        } catch (AccountException e) {
            switch (e.getExceptionReason()){
                case ACCOUNT_NOT_EXISTING:{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
                case ACCOUNT_CLOSED:{
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
                default:
                    break;
            }

        }

        return new ResponseEntity(transactionInfo, HttpStatus.CREATED);
    }

    @GetMapping("/account/{accountId}/transaction")
    public ResponseEntity getTransactions(@PathVariable ("accountId") String accountId,
                                          @RequestParam String fromDate,
                                      @RequestParam String toDate) {


        List<TransactionInfo> result = new ArrayList();

        try {
            result = transactionService.getTransactions(accountId, fromDate, toDate);
        } catch (AccountException e) {
            switch (e.getExceptionReason()) {
                case ACCOUNT_NOT_EXISTING: {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

                }
                default:
                    break;
            }
        }
        return new ResponseEntity(result, HttpStatus.OK);

    }
}
