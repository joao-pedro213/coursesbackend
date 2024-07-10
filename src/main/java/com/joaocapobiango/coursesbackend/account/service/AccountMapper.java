package com.joaocapobiango.coursesbackend.account.service;

import com.joaocapobiango.coursesbackend.account.dto.AccountPostRequest;
import com.joaocapobiango.coursesbackend.account.dto.AccountResponse;
import com.joaocapobiango.coursesbackend.account.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account postRequestToEntity(AccountPostRequest request);

    AccountResponse toResponse(Account account);

}