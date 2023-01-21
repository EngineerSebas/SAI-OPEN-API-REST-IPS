package com.spring.boot.sai.open.api.rest.controller;

import com.spring.boot.sai.open.api.rest.auth.JwtUtil;
import com.spring.boot.sai.open.api.rest.context.UserContext;
import com.spring.boot.sai.open.api.rest.dto.TokenResponse;
import com.spring.boot.sai.open.api.rest.dto.UserCredentials;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/get_tokens")
    @ResponseBody
    public ResponseEntity<TokenResponse> generateToken(@RequestBody UserCredentials userCredentials) throws Exception {
        if(userCredentials.getUsername().equals("admin") && userCredentials.getPassword().equals("admin")) {
            UserContext userContext = new UserContext();
            userContext.setLang("es_CO");
            userContext.setTz(false);
            userContext.setUid(2);
            String accessToken = jwtUtil.generateToken(userCredentials.getUsername(), userContext);
            String refreshToken = jwtUtil.generateRefreshToken(userCredentials.getUsername());
            Claims claims = Jwts.parser().setSigningKey(jwtUtil.getSecretKey()).parseClaimsJws(accessToken).getBody();
            TokenResponse tokenResponse = new TokenResponse(2, userContext, 1, accessToken, claims.getExpiration().getTime(), refreshToken, claims.getExpiration().getTime());
            return ResponseEntity.ok(tokenResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}