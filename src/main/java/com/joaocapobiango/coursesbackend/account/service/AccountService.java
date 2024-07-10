package com.joaocapobiango.coursesbackend.account.service;

import com.joaocapobiango.coursesbackend.account.dto.AccountPostRequest;
import com.joaocapobiango.coursesbackend.account.dto.AccountPutRequest;
import com.joaocapobiango.coursesbackend.account.dto.AccountResponse;
import com.joaocapobiango.coursesbackend.account.repository.AccountRepository;
import com.joaocapobiango.coursesbackend.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountMapper mapper;

    public AccountResponse create(AccountPostRequest request) {
        var accountToInsert = this.mapper.postRequestToEntity(request);
        var insertedAccount = this.repository.save(accountToInsert);
        return this.mapper.toResponse(insertedAccount);
    }

    public AccountResponse getById(Long id) {
        var foundAccount = this.repository.findById(id).orElseThrow(() -> new ResourceNotFound(id));
        return this.mapper.toResponse(foundAccount);
    }

    public AccountResponse update(Long id, AccountPutRequest request) {
        var accountToUpdate = this.repository.findById(id).orElseThrow(() -> new ResourceNotFound(id));
        if (request.getUsername() != null) {
            accountToUpdate.setUsername(request.getUsername());
        }
        if (request.getPassword() != null) {
            accountToUpdate.setPassword(request.getPassword());
        }
        var updatedAccount = this.repository.save(accountToUpdate);
        return this.mapper.toResponse(updatedAccount);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}