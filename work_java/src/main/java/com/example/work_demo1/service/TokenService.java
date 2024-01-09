package com.example.work_demo1.service;

import com.example.work_demo1.request.Request;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    private Key secretKey;
    private JwtParser jwtParser;

    @PostConstruct
    private void init() {
        String key = "VincentIsParticipatingItHomeIronmanContest";
        secretKey = Keys.hmacShaKeyFor(key.getBytes());
        jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    public Map<String, Object> parseToken(String token) {
        HashMap<String, Object> map = new HashMap<>();
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        map.put("claims", claims);
        System.out.println(claims);
        return map;
    }

    public String createToken(Request request) {
        String accessToken = createAccessToken(request.getAccount());

        return accessToken;
    }

    private String createAccessToken(String account) {
        // 有效時間（毫秒*1000）
        long expirationMillis = Instant.now()
                .plusSeconds(90)
                .getEpochSecond()
                * 1000;

        // 設置標準內容與自定義內容
        Claims claims = Jwts.claims();
        claims.setSubject("Access Token");
        claims.setIssuedAt(new Date());
        claims.setExpiration(new Date(expirationMillis));
        claims.put("account", account);

        // 簽名後產生 token
        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }
}
