package com.cvds.eci.laboratoryreservations.app_core.ModelTests;
import static org.junit.jupiter.api.Assertions.*;


import com.cvds.eci.laboratoryreservations.app_core.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;
    
    /**
     * Configura un objeto User antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        user = new User("Juan Pérez", "juan@example.com", "Estudiante", "password123");
    }
    
    /**
     * Verifica que los métodos getter devuelvan los valores correctos.
     */
    @Test
    void testGetters() {
        assertEquals("Juan Pérez", user.getName());
        assertEquals("juan@example.com", user.getEmail());
        assertEquals("Estudiante", user.getRol());
        assertEquals("password123", user.getPassword());
    }
    
    /**
     * Verifica que los métodos setter asignen correctamente los valores.
     */
    @Test
    void testSetters() {
        user.setName("Ana Gómez");
        user.setEmail("ana@example.com");
        user.setRol("Profesor");
        user.setPassword("newpass456");
        
        assertEquals("Ana Gómez", user.getName());
        assertEquals("ana@example.com", user.getEmail());
        assertEquals("Profesor", user.getRol());
        assertEquals("newpass456", user.getPassword());
    }
    
    /**
     * Verifica la asignación del ID al usuario.
     */
    @Test
    void testIdAssignment() {
        user.setId("98765");
        assertEquals("98765", user.getId());
    }
}