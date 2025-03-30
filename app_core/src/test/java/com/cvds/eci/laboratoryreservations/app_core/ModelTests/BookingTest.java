package com.cvds.eci.laboratoryreservations.app_core.ModelTests;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;

public class BookingTest {
    private Booking booking;
    
    /**
     * Configura un objeto Booking antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        booking = new Booking("Lab A", LocalDate.of(2024, 8, 21), LocalTime.of(14, 30), LocalTime.of(16, 30), "Reserva para prueba", 1);
    }
    
    /**
     * Verifica que los métodos getter devuelvan los valores correctos.
     */
    @Test
    void testGetters() {
        assertEquals("Lab A", booking.getLabName());
        assertEquals(LocalDate.of(2024, 8, 21), booking.getDate());
        assertEquals(LocalTime.of(14, 30), booking.getInitHour());
        assertEquals(LocalTime.of(16, 30), booking.getFinalHour());
        assertEquals("Reserva para prueba", booking.getDescription());
        assertEquals(1, booking.getPrioridad());
    }
    
    /**
     * Verifica que los métodos setter asignen correctamente los valores.
     */
    @Test
    void testSetters() {
        booking.setLabName("Lab B");
        booking.setDate(LocalDate.of(2024, 9, 15));
        booking.setInitHour(LocalTime.of(10, 0));
        booking.setFinalHour(LocalTime.of(12, 0));
        booking.setDescription("Nueva reserva");
        booking.setPrioridad(2);
        
        assertEquals("Lab B", booking.getLabName());
        assertEquals(LocalDate.of(2024, 9, 15), booking.getDate());
        assertEquals(LocalTime.of(10, 0), booking.getInitHour());
        assertEquals(LocalTime.of(12, 0), booking.getFinalHour());
        assertEquals("Nueva reserva", booking.getDescription());
        assertEquals(2, booking.getPrioridad());
    }
    
    /**
     * Verifica la asignación del ID a la reserva.
     */
    @Test
    void testIdAssignment() {
        booking.setId("12345");
        assertEquals("12345", booking.getId());
    }
}
