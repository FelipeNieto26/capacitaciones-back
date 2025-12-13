package com.prueba.capacitaciones.utils;

import com.prueba.capacitaciones.dto.login.LoginRequestDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtServices {

    @Value("${jwt.secrets}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(LoginRequestDto loginRequestDto, String nombre, long idUser, boolean isAdmin) {
        try {
            return Jwts.builder()
                    .setSubject(loginRequestDto.getUsername())
                    .claim("nombre", nombre)
                    .claim("idUser", idUser)
                    .claim("isAdmin", isAdmin)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el token: " + e.getMessage());
        }
    }


    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (ExpiredJwtException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al validar el token: " + e.getMessage());
        }
    }

    public Claims decodeToken(String token) {
        try {
            String cleanToken = token.replace("Bearer", "").trim().replaceAll("\\s+", "");

            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(cleanToken)
                    .getBody();

        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("El token ha expirado", e);
        } catch (io.jsonwebtoken.security.SecurityException e) {
            throw new IllegalArgumentException("Firma del token inválida", e);
        } catch (io.jsonwebtoken.MalformedJwtException e) {
            throw new IllegalArgumentException("Token mal formado", e);
        } catch (io.jsonwebtoken.UnsupportedJwtException e) {
            throw new IllegalArgumentException("Token no soportado", e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al decodificar el token: " + e.getMessage(), e);
        }
    }


    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
