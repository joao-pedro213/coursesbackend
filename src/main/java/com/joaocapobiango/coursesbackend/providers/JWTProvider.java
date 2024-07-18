package com.joaocapobiango.coursesbackend.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {

    @Value("${security.token.secret}")
    private String secretKey;

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");
        var algorithm = Algorithm.HMAC256(this.secretKey);
        try {
            var subject = JWT
                .require(algorithm)
                .build()
                .verify(token)
                .getSubject();
            return subject;
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

}