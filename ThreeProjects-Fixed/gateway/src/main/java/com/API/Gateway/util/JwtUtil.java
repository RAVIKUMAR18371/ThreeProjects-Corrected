package com.API.Gateway.util;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // MUST be EXACTLY SAME as Auth Service
    public static final String SECRET =
            "W2nocW+G/6uwB20n7yyKY8QRn8XhRNj4+/ECVo2d8RI=";

    public void validateToken(final String token) {

        Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token);

    }

    private Key getSignKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET);

        return Keys.hmacShaKeyFor(keyBytes);

    }
}
