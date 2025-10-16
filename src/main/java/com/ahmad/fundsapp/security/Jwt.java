package com.ahmad.fundsapp.security;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class Jwt {

     private final Key SECRET_KEY;
     private final long EXPIRATION_TIME = 864000000;
    public Jwt() {
        Dotenv dotenv = null;
        try {
            dotenv = Dotenv.load();
        } catch (Exception e) {
            throw e;
        }

        String secret = (dotenv != null ? dotenv.get("JWT_SECRET") : System.getenv("JWT_SECRET"));
        if (secret == null) {
            throw new RuntimeException("JWT_SECRET not set in environment");
        }

        this.SECRET_KEY = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

  public String generateToken(String username) {
      return Jwts.builder()
              .setSubject(username)
              .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
              .signWith(SECRET_KEY)
              .compact();
  }

  public boolean validateToken(String token , String username) {
      try {
          String extractedUsername = extractUsername(token);
          return (extractedUsername.equals(username) && !isTokenValid(token)) ;
      }catch(Exception e) {
          System.out.println(e.getMessage());
          return false;
      }
  }

  public String extractUsername(String token) {
      return Jwts.parserBuilder()
              .setSigningKey(SECRET_KEY)
              .build()
              .parseClaimsJws(token).getBody().getSubject();
  }

  public boolean isTokenValid(String token) {
      return extractExpiration(token).before(new Date());
  }

  public Date extractExpiration(String token) {
      return Jwts.parserBuilder()
              .setSigningKey(SECRET_KEY)
              .build().parseClaimsJws(token).getBody().getExpiration();
  }

}
