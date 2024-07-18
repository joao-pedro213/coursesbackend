package com.joaocapobiango.coursesbackend.account.controller;

import com.joaocapobiango.coursesbackend.account.dto.AccountAuthentication;
import com.joaocapobiango.coursesbackend.account.service.AccountAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/app/auth/account")
@RestController
public class AccountAuthenticationController {

    @Autowired
    AccountAuthenticationService accountAuthenticationService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody AccountAuthentication accountAuthentication) {
        try {
            var token = this.accountAuthenticationService.authenticate(accountAuthentication);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
        }
    }

}