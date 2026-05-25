package org.nessrev.taskmanagementsystem.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.nessrev.taskmanagementsystem.exception.custom.TokenHashingException;
import org.nessrev.taskmanagementsystem.jwt.entity.RefreshToken;
import org.nessrev.taskmanagementsystem.jwt.repo.RefreshTokenRepository;
import org.nessrev.taskmanagementsystem.jwt.userSecurity.entity.UserSecurity;
import org.nessrev.taskmanagementsystem.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final String secret;

    public JwtService(
            RefreshTokenRepository refreshTokenRepository,
            @Value("${jwt.secret}")
            String secret) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.secret = secret;
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000)) //15 min
                .signWith(getSigningKey())
                .compact();
    }

    public String generateRefreshToken(User user) {
        Date issuedAt = new Date();
        Date expiry = new Date(
                System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L // 7 days
        );

        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole().name())
                .setIssuedAt(issuedAt)
                .setExpiration(expiry)
                .signWith(getSigningKey())
                .compact();

        addRefreshTokenToRepository(
                token,
                expiry,
                issuedAt,
                user
        );
        return token;
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserSecurity userSecurity){
        final String username = extractUsername(token);

        return username.equals(userSecurity.getUsername())
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private void addRefreshTokenToRepository(
            String token,
            Date expiry,
            Date issuedAt,
            User user){

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);
        refreshToken.setTokenHash(hashToken(token));
        refreshToken.setIssuedAt(issuedAt);
        refreshToken.setExpiresAt(expiry);
        refreshToken.setRevoked(false);

        refreshTokenRepository.deleteAllByUser(user);
        refreshTokenRepository.save(refreshToken);
    }

    private String hashToken(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new TokenHashingException(e);
        }
    }
}
