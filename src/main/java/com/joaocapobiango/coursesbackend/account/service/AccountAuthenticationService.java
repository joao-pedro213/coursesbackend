package com.joaocapobiango.coursesbackend.account.service;

import javax.naming.AuthenticationException;

import com.joaocapobiango.coursesbackend.account.dto.AccountAuthentication;
import com.joaocapobiango.coursesbackend.account.repository.AccountRepository;
import com.joaocapobiango.coursesbackend.providers.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class AccountAuthenticationService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;

    public String authenticate(AccountAuthentication accountAuthentication) throws AuthenticationException {
        var account = this.repository
            .findByUsername(accountAuthentication.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("Account username/password incorrect"));
        var passwordMatches = this.passwordEncoder
            .matches(accountAuthentication.getPassword(), account.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }
        return this.jwtProvider.createToken(account.getId().toString(), null);
    }

}