package com.cvds.eci.laboratoryreservations.app_core.ServiceTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cvds.eci.laboratoryreservations.app_core.model.User;
import com.cvds.eci.laboratoryreservations.app_core.repository.UserRepository;
import com.cvds.eci.laboratoryreservations.app_core.service.JWTService;
import com.cvds.eci.laboratoryreservations.app_core.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private UserService userService;

    private User user;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @BeforeEach
    void setUp() {
        user = new User("testUser", "test@example.com", "USER", "password123");
    }

    @Test
    void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);
        
        List<User> result = userService.getAllUsers();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void testGetUserByIdSuccess() {
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        
        User foundUser = userService.getUserById("1");
        assertNotNull(foundUser);
        assertEquals("testUser", foundUser.getName());
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userRepository.findById("2")).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(RuntimeException.class, () -> userService.getUserById("2"));
        assertEquals("The user with the id: 2 cannot be found", exception.getMessage());
    }

    @Test
    void testAddUserSuccess() {
        when(userRepository.findByEmailAndName(user.getEmail(), user.getName())).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(user);
        
        User savedUser = userService.addUser(user);
        assertNotNull(savedUser);
        assertEquals("testUser", savedUser.getName());
    }

    @Test
    void testAddUserAlreadyExists() {
        when(userRepository.findByEmailAndName(user.getEmail(), user.getName())).thenReturn(user);
        
        Exception exception = assertThrows(RuntimeException.class, () -> userService.addUser(user));
        assertEquals("The user testUser and mail test@example.com already exists .", exception.getMessage());
    }

    @Test
    void testDeleteUserSuccess() {
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById("1");
        
        String deletedId = userService.deleteUser("1");
        assertEquals("1", deletedId);
    }

    @Test
    void testDeleteUserNotFound() {
        when(userRepository.findById("2")).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(RuntimeException.class, () -> userService.deleteUser("2"));
        assertEquals("The user does not exist", exception.getMessage());
    }


    @Test
void testGetUserByNameSuccess() {
    when(userRepository.findByName("testUser")).thenReturn(user);
    
    User foundUser = userService.getUserByName("testUser");
    assertNotNull(foundUser);
    assertEquals("testUser", foundUser.getName());
}

@Test
void testGetUserByNameNotFound() {
    when(userRepository.findByName("unknownUser")).thenReturn(null);
    
    Exception exception = assertThrows(RuntimeException.class, () -> userService.getUserByName("unknownUser"));
    assertEquals("The user unknownUser cannot be found", exception.getMessage());
}

@Test
void testLoadUserByUsernameSuccess() {
    when(userRepository.findByName("testUser")).thenReturn(user);
    
    UserDetails userDetails = userService.loadUserByUsername("testUser");
    assertNotNull(userDetails);
    assertEquals("testUser", userDetails.getUsername());
}







@Test
void testUpdateUserSuccess() {
    User updatedUser = new User("updatedUser", "updated@example.com", "USER", "password123");
    when(userRepository.findById("1")).thenReturn(Optional.of(user));
    when(userRepository.save(any(User.class))).thenReturn(updatedUser);
    
    userService.updateUser("1", updatedUser);
    verify(userRepository, times(1)).save(any(User.class));
}

@Test
void testUpdateUserNotFound() {
    when(userRepository.findById("2")).thenReturn(Optional.empty());
    
    Exception exception = assertThrows(RuntimeException.class, () -> userService.updateUser("2", user));
    assertEquals("User with ID 2 not found", exception.getMessage());
}

}
