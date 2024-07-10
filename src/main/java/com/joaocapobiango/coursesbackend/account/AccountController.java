package com.joaocapobiango.coursesbackend.account;

import com.joaocapobiango.coursesbackend.account.dto.AccountPostRequest;
import com.joaocapobiango.coursesbackend.account.dto.AccountPutRequest;
import com.joaocapobiango.coursesbackend.account.dto.AccountResponse;
import com.joaocapobiango.coursesbackend.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/app/account")
@RestController
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping
    public ResponseEntity<AccountResponse> create(@RequestBody AccountPostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> update(@PathVariable Long id, @RequestBody AccountPutRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}