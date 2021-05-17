package com.account.transactions.controller;

import com.account.transactions.common.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyLoginController {

    @Autowired
    LoggedUser loggedUser;


    /**
     *
     * @param userId
     * Very dummy login implemented only to illustrate the final scope of obtaining a LoggedUser kind of object to
     * be used cross services
     */
    @PostMapping("/login/{userId}")
    public ResponseEntity login(@PathVariable("userId") String userId){
        loggedUser.setUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
