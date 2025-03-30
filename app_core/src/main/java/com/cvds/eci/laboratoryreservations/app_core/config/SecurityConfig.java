package com.cvds.eci.laboratoryreservations.app_core.config;

import java.util.Arrays;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.cvds.eci.laboratoryreservations.app_core.service.UserDetailService;

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
    private UserDetailService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;
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

                // Configuración de CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                
                // Disables CSRF protection to avoid issues in clients that do not handle CSRF tokens.
                
                .csrf(customizer -> customizer.disable())


                // Requires authentication for all requests to the server.
                .authorizeHttpRequests(request -> request
                .requestMatchers("/login")
                .permitAll()
                .anyRequest().authenticated())

                // Enables a default login form provided by Spring Security.
                //.formLogin(Customizer.withDefaults())

                // Enables HTTP Basic authentication for clients that do not use the login form.
                .httpBasic(Customizer.withDefaults())

                // Configures session management as STATELESS, meaning the server
                // will not store user session information. This improves security in RESTful APIs.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
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

/**
 * Creates and configures an AuthenticationManager using Spring Security's authentication configuration.
 * The AuthenticationManager is a central interface in Spring Security for authenticating users.
 *
 * @param config A pre-built AuthenticationConfiguration object provided by Spring Security which
 *               allows customization and retrieval of authentication-related settings.
 * @return An AuthenticationManager that is configured to handle the authentication process.
 * @throws Exception If there is an issue with obtaining the AuthenticationManager from the configuration.
 */
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
}

}
