package com.cvds.eci.laboratoryreservations.app_core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for the application using Spring Security.
 * 
 * This class defines a custom security configuration for the application,
 * specifying authentication policies, session management, and CSRF protection.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Service that handles loading user details from the database or any other source.
     * It is automatically injected for use in authentication.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Configures the Spring Security filter chain to handle authentication and authorization.
     * 
     * @param http The Spring Security configuration object.
     * @return A configured SecurityFilterChain for the application.
     * @throws Exception If an error occurs while building the security configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Disables CSRF protection to avoid issues in clients that do not handle CSRF tokens.
                
                .csrf(customizer -> customizer.disable())


                // Requires authentication for all requests to the server.
                .authorizeHttpRequests(request -> request
                .requestMatchers("login")
                .permitAll()
                .anyRequest().authenticated())

                // Enables a default login form provided by Spring Security.
                //.formLogin(Customizer.withDefaults())

                // Enables HTTP Basic authentication for clients that do not use the login form.
                .httpBasic(Customizer.withDefaults())

                // Configures session management as STATELESS, meaning the server
                // will not store user session information. This improves security in RESTful APIs.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    /**
     * Configures the authentication provider, setting the authentication service
     * and password encoder.
     * 
     * @return An AuthenticationProvider configured with a DaoAuthenticationProvider.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();

        // Defines the password encoding mechanism (NoOpPasswordEncoder is insecure and should only be used for testing).
        daoProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        // Sets the service that provides user details.
        daoProvider.setUserDetailsService(userDetailsService);

        return daoProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}
