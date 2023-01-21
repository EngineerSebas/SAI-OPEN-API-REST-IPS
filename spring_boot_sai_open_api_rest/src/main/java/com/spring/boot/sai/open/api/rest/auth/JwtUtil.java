package com.spring.boot.sai.open.api.rest.auth;

import com.spring.boot.sai.open.api.rest.context.UserContext;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {
    @Autowired
    private Environment env;

    public String generateToken(String username, UserContext userContext) {
        long expirationTime = Long.parseLong(env.getProperty("access_token_expiration_time")) * 1000;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + expirationTime;
        Date exp = new Date(expMillis);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, env.getProperty("secretKey"))
                .compact();
    }

    public String generateRefreshToken(String username) {
        long refreshExpirationTime = Long.parseLong(env.getProperty("refresh_token_expiration_time")) * 1000;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + refreshExpirationTime;
        Date exp = new Date(expMillis);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, env.getProperty("secretKey"))
                .compact();
    }
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(env.getProperty("secretKey"))
                    .parseClaimsJws(token)
                    .getBody();
            Date expiration = claims.getExpiration();
            if (expiration.before(new Date())) {
                System.out.println("Token has expired");
                return false;
            }
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    public String getSecretKey() {
        return env.getProperty("secretKey");
    }
}