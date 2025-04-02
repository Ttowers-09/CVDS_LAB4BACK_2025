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

    @BeforeEach
    void setUp() {
        mockUser = Mockito.mock(User.class);
        Mockito.when(mockUser.getName()).thenReturn("testUser");
        Mockito.when(mockUser.getPassword()).thenReturn("testPassword");
        Mockito.when(mockUser.getRol()).thenReturn("ADMIN");
        usersDetails = new UsersDetails(mockUser);
    }

    @Test
    void testGetUsername() {
        assertEquals("testUser", usersDetails.getUsername());
    }

    @Test
    void testGetPassword() {
        assertEquals("testPassword", usersDetails.getPassword());
    }

    @Test
    void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = usersDetails.getAuthorities();
        assertEquals(Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")), authorities);
    }

    @Test
    void testIsAccountNonExpired() {
        assertTrue(usersDetails.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        assertTrue(usersDetails.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        assertTrue(usersDetails.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        assertTrue(usersDetails.isEnabled());

    }


}
