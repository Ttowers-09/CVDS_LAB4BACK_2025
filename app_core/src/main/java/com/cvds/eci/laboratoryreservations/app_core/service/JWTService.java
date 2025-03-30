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
 * Servicio para la generación, validación y gestión de tokens JSON Web Token (JWT).
 * Se utiliza para la autenticación de usuarios en la aplicación.
 */
@Service
public class JWTService {
    
    private String key = "";

    /**
     * Constructor de {@code JWTService}.
     * Inicializa el servicio generando una clave secreta utilizando el algoritmo HmacSHA256.
     */
    public JWTService() {
        try {
            // Genera una clave secreta aleatoria para la firma de los JWT
            KeyGenerator keygen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keygen.generateKey();
            // Codifica la clave en Base64 para garantizar su almacenamiento y transmisión segura
            key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera un token JWT para un usuario determinado.
     *
     * @param name Nombre del usuario para el cual se generará el token.
     * @return Un token JWT firmado como una cadena de texto.
     */
    public String generateToken(String name) {
        Map<String, Object> claims = new HashMap<>();

        // Construcción del JWT con claims, sujeto, fecha de emisión y expiración
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(name)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // Expira en 1 hora
                .and()
                .signWith(getKey())
                .compact();
    }

    /**
     * Obtiene la clave secreta utilizada para firmar los tokens JWT.
     *
     * @return Clave secreta en formato {@link SecretKey}.
     */
    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Extrae el nombre de usuario desde un token JWT.
     *
     * @param token El JWT del cual se extraerá el nombre de usuario.
     * @return El nombre de usuario contenido en el token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae un claim específico del token JWT.
     *
     * @param token         El JWT del cual se extraerán los claims.
     * @param claimResolver Función para resolver el claim específico.
     * @param <T>           Tipo del claim a extraer.
     * @return El valor del claim extraído.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    /**
     * Valida un token JWT verificando que el nombre de usuario coincida y que no haya expirado.
     *
     * @param token El JWT a validar.
     * @param user  Objeto {@link UserDetails} con los datos del usuario autenticado.
     * @return {@code true} si el token es válido, {@code false} en caso contrario.
     */
    public boolean validateToken(String token, UserDetails user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Extrae todos los claims contenidos en un token JWT.
     *
     * @param token El JWT del cual se extraerán los claims.
     * @return Objeto {@link Claims} con todos los claims del token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                   .verifyWith(getKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }

    /**
     * Verifica si un token JWT ha expirado.
     *
     * @param token El JWT a verificar.
     * @return {@code true} si el token ha expirado, {@code false} en caso contrario.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrae la fecha de expiración de un token JWT.
     *
     * @param token El JWT del cual se extraerá la fecha de expiración.
     * @return La fecha de expiración del token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
