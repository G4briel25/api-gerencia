package com.administrador.api_gerencia.security.jwt;

import com.administrador.api_gerencia.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${api_gerencia.jwtSecret}")
    private String jwtSecret;

    @Value("${api_gerencia.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername()) // Nome de usuário como subject
                .issuedAt(new Date()) // Data de emissão do token
                .expiration(new Date(new Date().getTime() + jwtExpirationMs)) // Data de expiração
                .signWith(getSigninKey(), SignatureAlgorithm.HS256) // Assina com a chave secreta
                .compact();
    }

    public Key getSigninKey() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return key;
    }

    public String getUsernameToken(String token) {
        return Jwts.parser().setSigningKey(getSigninKey()).build().parseSignedClaims(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(authToken);
            return true;

        } catch (MalformedJwtException e) {
            System.out.println("Token inválido " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Token não suportado " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Token Argumento inválido " + e.getMessage());
        }

        return false;
    }

}
