package com.example.productmanager.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

  // Clé de 64 octets minimum (512 bits) => ici 64 caractères ASCII
  private static final String SECRET_KEY_STRING = "MaCleUltraSecreteEtComplexePourHS512_AUtiliserEnProduction_XYZ123456789";
  
  // Convertir en clé HMAC SHA
  private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes(StandardCharsets.UTF_8));

  public String generateToken(Authentication authentication) {
    UserDetails user = (UserDetails) authentication.getPrincipal();
    return Jwts.builder()
      .setSubject(user.getUsername())
      .claim("roles", user.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority)
          .collect(Collectors.toList()))
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
      .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
      .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(SECRET_KEY)
      .build()
      .parseClaimsJws(token)
      .getBody()
      .getSubject();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    String username = extractUsername(token);
    return username.equals(userDetails.getUsername());
  }
}