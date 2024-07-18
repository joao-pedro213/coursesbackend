package com.joaocapobiango.coursesbackend.account.service;

import javax.naming.AuthenticationException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.joaocapobiango.coursesbackend.account.dto.AccountAuthentication;
import com.joaocapobiango.coursesbackend.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class AccountAuthenticationService {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private AccountRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticate(AccountAuthentication accountAuthentication) throws AuthenticationException {
        var account = this.repository
            .findByUsername(accountAuthentication.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("Account username/password incorrect"));
        var passwordMatches = this.passwordEncoder.matches(accountAuthentication.getPassword(), account.getPassword());
        if (!passwordMatches) {
            throw new AuthenticationException();
        }
        var algorithm = Algorithm.HMAC256(this.secretKey);
        var token = JWT
            .create()
            .withIssuer("CoursesAPI")
            .withSubject(account.getId().toString())
            .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
            .sign(algorithm);
        return token;
    }

}