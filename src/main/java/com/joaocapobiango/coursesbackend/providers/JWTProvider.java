package com.joaocapobiango.coursesbackend.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class JWTProvider {

    @Value("${security.token.secret}")
    private String secretKey;

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(this.secretKey);
    }

    public String createToken(String accountId, String role) {
        return JWT
            .create()
            .withIssuer("CoursesAPI")
            .withSubject(accountId)
            .withExpiresAt(Instant.now().plus(Duration.ofMinutes(10)))
            .sign(this.getAlgorithm());
    }

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");
        try {
            var subject = JWT
                .require(this.getAlgorithm())
                .build()
                .verify(token)
                .getSubject();
            return subject;
        } catch (JWTVerificationException exception) {
            return "";
        }
    }
}