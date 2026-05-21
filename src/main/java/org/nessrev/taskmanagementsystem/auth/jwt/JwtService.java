package org.nessrev.taskmanagementsystem.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.nessrev.taskmanagementsystem.user.entity.User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {

    private final String SECRET = "ThisIsMySuperPuperSecretKeyItIsVeryLong";

    //строка secret превращается в байты и создается криптографический ключ
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateJwtToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername()) // кому
                .claim("role", user.getRole().name()) //что я добавляю в свой токен
                .setIssuedAt(new Date()) // время создания
                .setExpiration(new Date(System.currentTimeMillis() + 600000)) // время жизни (мс)
                .signWith(getSigningKey())
                .compact();
    }
}
