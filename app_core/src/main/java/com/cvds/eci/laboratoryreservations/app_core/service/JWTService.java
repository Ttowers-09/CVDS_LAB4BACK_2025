package com.cvds.eci.laboratoryreservations.app_core.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Service class for generating and managing JSON Web Tokens (JWT).
 */
@Service
public class JWTService {
    
    private String key = "";

     /**
     * Constructor for JWTService.
     * Initializes the service by generating a secret key using the HmacSHA256 algorithm.
     */
    public JWTService() {
        try {
            // KeyGenerator is used to generate a random key for JWT signing
            KeyGenerator keygen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keygen.generateKey();
            // Encode the key using Base64 to ensure it can be safely transmitted and stored
            key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            
            e.printStackTrace();
        }
    }

    
    /**
     * Generates a JWT for a given subject using the stored key.
     * 
     * @param name the subject for which the token is to be generated
     * @return a signed JWT as a String
     */
    public String generateToken(String name) {
        Map<String,Object> claims = new HashMap<>();

        // Build the JWT with claims, subject, issued date, expiration, and signing key
        return Jwts.builder()
        .claims()
        .add(claims)
        .subject(name)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 10))
        .and()
        .signWith(getKey())
        .compact();
    }
    

    /**
     * Retrieves the signing key from the encoded Base64 string stored during initialization.
     * 
     * @return Key object to be used for signing JWTs
     */
    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    /**
 * Extracts the username from the JWT.
 * 
 * @param token the JWT from which the username is to be extracted
 * @return the username as a String
 */
public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
}

/**
 * Generic method to extract a specific claim from the token.
 * 
 * @param token the JWT from which claims are to be extracted
 * @param claimResolver a function to extract a particular claim
 * @param <T> the type of the claim to be extracted
 * @return the extracted claim
 */
private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = extractAllClaims(token);
    return claimResolver.apply(claims);
}

/**
 * Validates the token by checking the username and expiration.
 * 
 * @param token the JWT to validate
 * @param user the UserDetails object to compare with the username in the token
 * @return true if the token is valid, false otherwise
 */
public boolean validateToken(String token, UserDetails user) {
    final String username = extractUsername(token);
    return (username.equals(user.getUsername()) && !isTokenExpired(token));
}

/**
 * Extracts all claims from a token.
 * 
 * @param token the JWT from which claims are to be extracted
 * @return Claims object containing all the claims from the token
 */
private Claims extractAllClaims(String token) {
    return Jwts.parser()
               .verifyWith(getKey())
               .build()
               .parseSignedClaims(token)
               .getPayload();
               
}

/**
 * Checks if the token has expired.
 * 
 * @param token the JWT to check
 * @return true if the token has expired, false otherwise
 */
private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
}

/**
 * Extracts the expiration date from the token.
 * 
 * @param token the JWT from which the expiration date is to be extracted
 * @return the expiration date
 */
private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
}

    
}
