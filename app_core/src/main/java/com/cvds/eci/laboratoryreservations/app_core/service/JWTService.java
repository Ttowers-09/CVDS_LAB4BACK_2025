package com.cvds.eci.laboratoryreservations.app_core.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

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
            // TODO Auto-generated catch block
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
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}
