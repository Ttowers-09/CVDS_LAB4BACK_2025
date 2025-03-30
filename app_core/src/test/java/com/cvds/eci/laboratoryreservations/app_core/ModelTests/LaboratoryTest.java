package com.cvds.eci.laboratoryreservations.app_core.ModelTests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cvds.eci.laboratoryreservations.app_core.model.Laboratory;

public class LaboratoryTest {

    private Laboratory laboratory;
    
    /**
     * Configura un objeto Laboratory antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        laboratory = new Laboratory("Lab A", "Edificio 1, Piso 2", 30, true);
    }
    
    /**
     * Verifica que los métodos getter devuelvan los valores correctos.
     */
    @Test
    void testGetters() {
        assertEquals("Lab A", laboratory.getName());
        assertEquals("Edificio 1, Piso 2", laboratory.getLocation());
        assertEquals(30, laboratory.getCapacity());
        assertTrue(laboratory.isAvailable());
    }
    
    /**
     * Verifica que los métodos setter asignen correctamente los valores.
     */
    @Test
    void testSetters() {
        laboratory.setName("Lab B");
        laboratory.setLocation("Edificio 2, Piso 3");
        laboratory.setCapacity(40);
        laboratory.setAvailable(false);
        
        assertEquals("Lab B", laboratory.getName());
        assertEquals("Edificio 2, Piso 3", laboratory.getLocation());
        assertEquals(40, laboratory.getCapacity());
        assertFalse(laboratory.isAvailable());
    }
    
    /**
     * Verifica la asignación del ID al laboratorio.
     */
    @Test
    void testIdAssignment() {
        laboratory.setId("12345");
        assertEquals("12345", laboratory.getId());
    }
    
    /**
     * Verifica que el método toString devuelva una cadena con la información correcta.
     */
    @Test
    void testToString() {
        String expected = "Laboratory{nombre='Lab A', ubicación='Edificio 1, Piso 2', capacidad=30, disponible=true}";
        assertEquals(expected, laboratory.toString());
    }
}
