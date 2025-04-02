package com.cvds.eci.laboratoryreservations.app_core.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cvds.eci.laboratoryreservations.app_core.service.JWTService;
import com.cvds.eci.laboratoryreservations.app_core.service.UserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWT authentication filter to authenticate requests based on token provided in the Authorization header.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext context;

    /**
     * Filters each HTTP request to the server.
     * 
     * @param request The request from the client.
     * @param response The response from the server.
     * @param filterChain The filter chain to continue the filter process.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Extract the Authorization header from the request.
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        // Check if the Authorization header is valid and extract the token.
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        // Authenticate the user if the username is found and there is no authentication set.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load the user details using the username.
            UserDetails user = context.getBean(UserDetailService.class).loadUserByUsername(username);

            // Validate the token using the user details.
            if (jwtService.validateToken(token, user)) {
                // Create a new authentication token.
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                // Set details about the authentication request.
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication in the context.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Continue the filter chain.
        filterChain.doFilter(request, response);
    }

}
