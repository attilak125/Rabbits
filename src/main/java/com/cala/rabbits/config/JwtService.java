package com.cala.rabbits.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private final Environment environment;

  private final String SECRET_KEY;

  public JwtService(Environment environment) {
    this.environment = environment;
    this.SECRET_KEY = environment.getProperty("spring.auth.code");
  }

  public String extractUsername(String token) {
    return extractClaim(token,Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(UserDetails userDetails){
    return generateToken(new HashMap<>(),userDetails);
  }

  public String generateToken(Map<String, Objects> extractClaims, UserDetails userDetails){
    return Jwts.builder()
        .setClaims(extractClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 24))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }
  
  public boolean isTokenValid(String token, UserDetails userDetails){
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) && isUserEnabled(userDetails);
  }

  private boolean isUserEnabled(UserDetails userDetails) {
    return userDetails.isEnabled();
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date(System.currentTimeMillis()));
  }

  private Date extractExpiration(String token) {
    return extractClaim(token,Claims::getExpiration);
  }

  private Claims extractAllClaims(String token){
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSignInKey() {
    byte[] keyBaytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBaytes);
  }
}
