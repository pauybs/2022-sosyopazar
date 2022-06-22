package com.mcavlak.sosyobazaar.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtToken {

    public static final String SIGNING_KEY = "G78}N@8#&e.>`2Z)2b5gsdGgSsd423+%t7p-Kvq-PX(S}}t/M;9%";
    public static final String ISSUER = "https://fatihmayuk.com";

    private final String token;
    private Claims claims;

    public static JwtToken parse(String token) {
        return new JwtToken(token);
    }

    private JwtToken(String token) {
        this.token = token;
        this.claims = Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims getClaims() {
        return claims;
    }

    public String getToken() {
        return token;
    }

    public boolean isNoneExpired() {
        return claims.getExpiration().after(new Date());
    }

    public static class Builder {

        private Claims claims;

        public Builder() {
            claims = Jwts.claims();
            claims.setIssuedAt(new Date());
        }

        public Builder put(String key, Object obj) {
            claims.put(key, obj);
            return this;
        }

        public Builder subject(String subject) {
            claims.setSubject(subject);
            return this;
        }

        public Builder expiration(Date date) {
            claims.setExpiration(date);
            return this;
        }

        public String build() {

            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuer(ISSUER)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                    .compact();
        }
    }

}
