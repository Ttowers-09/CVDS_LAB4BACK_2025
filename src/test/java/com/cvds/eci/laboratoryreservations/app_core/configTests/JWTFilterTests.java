package com.cvds.eci.laboratoryreservations.app_core.configTests;

import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.cvds.eci.laboratoryreservations.app_core.config.JwtFilter;
import com.cvds.eci.laboratoryreservations.app_core.service.JWTService;
import com.cvds.eci.laboratoryreservations.app_core.service.UserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
class JwtFilterTests {

    @InjectMocks
    private JwtFilter jwtFilter;

    @Mock
    private JWTService jwtService;

    @Mock
    private ApplicationContext context;

    @Mock
    private UserDetailService userDetailService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext(); // Limpia el contexto de seguridad antes de cada prueba
    }

    @Test
    void shouldContinueFilterWhenNoAuthHeader() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtFilter.doFilter(request, response, filterChain);

        verify(jwtService, never()).extractUsername(any());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void shouldNotAuthenticateWhenInvalidToken() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn("Bearer invalidToken");
        when(jwtService.extractUsername("invalidToken")).thenReturn(null);

        jwtFilter.doFilter(request, response, filterChain);

        verify(jwtService).extractUsername("invalidToken");
        verify(filterChain).doFilter(request, response);
        verify(context, never()).getBean(UserDetailService.class);
    }

    @Test
    void shouldAuthenticateWhenValidToken() throws ServletException, IOException {
        String validToken = "validToken";
        String username = "testUser";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + validToken);
        when(jwtService.extractUsername(validToken)).thenReturn(username);
        when(context.getBean(UserDetailService.class)).thenReturn(userDetailService);
        when(userDetailService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtService.validateToken(validToken, userDetails)).thenReturn(true);

        jwtFilter.doFilter(request, response, filterChain);

        verify(jwtService).extractUsername(validToken);
        verify(context).getBean(UserDetailService.class);
        verify(userDetailService).loadUserByUsername(username);
        verify(jwtService).validateToken(validToken, userDetails);
        verify(filterChain).doFilter(request, response);
    }
}
