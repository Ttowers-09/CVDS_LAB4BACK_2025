package com.cvds.eci.laboratoryreservations.app_core.ModelTests;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.cvds.eci.laboratoryreservations.app_core.model.User;
import com.cvds.eci.laboratoryreservations.app_core.model.UsersDetails;
import com.cvds.eci.laboratoryreservations.app_core.repository.UserRepository;
import com.cvds.eci.laboratoryreservations.app_core.service.UserDetailService;

/**
 * Pruebas unitarias para la clase {@link UsersDetails}.
 */
public class UserDetailsTests {
    @Mock
    private UserRepository userRepository;

    private User mockUser;
    private UsersDetails usersDetails;
    @InjectMocks
    private UserDetailService userDetailService;
    
    /**
     * Configuración inicial antes de cada prueba.
     * Crea un usuario mock y lo utiliza para inicializar un objeto UsersDetails.
     */
    @BeforeEach
    void setUp() {
        mockUser = Mockito.mock(User.class);
        Mockito.when(mockUser.getName()).thenReturn("testUser");
        Mockito.when(mockUser.getPassword()).thenReturn("testPassword");
        Mockito.when(mockUser.getRol()).thenReturn("ADMIN");
        usersDetails = new UsersDetails(mockUser);
    }

    /**
     * Prueba unitaria para verificar que el método getUsername() devuelve el nombre esperado.
     */

    @Test
    void testGetUsername() {
        assertEquals("testUser", usersDetails.getUsername());
    }

    /**
     * Prueba unitaria para verificar que el método getPassword() devuelve la contraseña esperada.
     */
    @Test
    void testGetPassword() {
        assertEquals("testPassword", usersDetails.getPassword());
    }

    /**
     * Prueba unitaria para verificar que el método getAuthorities() devuelve la autoridad correcta.
     */
    @Test
    void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = usersDetails.getAuthorities();
        assertEquals(Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")), authorities);
    }

    /**
     * Prueba unitaria para verificar que la cuenta no está expirada.
     */
    @Test
    void testIsAccountNonExpired() {
        assertTrue(usersDetails.isAccountNonExpired());
    }

    /**
     * Prueba unitaria para verificar que la cuenta no está bloqueada.
     */
    @Test
    void testIsAccountNonLocked() {
        assertTrue(usersDetails.isAccountNonLocked());
    }

    /**
     * Prueba unitaria para verificar que las credenciales no están expiradas.
     */
    @Test
    void testIsCredentialsNonExpired() {
        assertTrue(usersDetails.isCredentialsNonExpired());
    }

    /**
     * Prueba unitaria para verificar que la cuenta está habilitada.
     */
    @Test
    void testIsEnabled() {
        assertTrue(usersDetails.isEnabled());

    }


}
