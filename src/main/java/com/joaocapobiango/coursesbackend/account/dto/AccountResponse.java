package com.joaocapobiango.coursesbackend.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private Long id;

    private String email;

    private String username;

    private String password;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}