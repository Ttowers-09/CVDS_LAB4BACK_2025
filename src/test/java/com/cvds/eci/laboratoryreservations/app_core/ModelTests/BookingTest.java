package com.cvds.eci.laboratoryreservations.app_core.ModelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;

/**
 * Pruebas unitarias para la clase Booking.
 */
public class BookingTest {

    private Booking booking;


    /**
     * Configuración inicial antes de cada prueba.
     * Crea un objeto Booking con valores predeterminados para ser utilizado en las pruebas.
     */
    @BeforeEach
    void setUp() {
        booking = new Booking("Lab A", LocalDate.of(2024, 8, 21),
                LocalTime.of(14, 30), LocalTime.of(16, 30), "Reserva de prueba", 1, "user123");
    }

    /**
     * Prueba unitaria para verificar la correcta creación de un objeto Booking.
     * Se asegura de que los valores iniciales del objeto sean los esperados.
     */

    @Test
    void testBookingCreation() {
        assertNotNull(booking);
        assertEquals("Lab A", booking.getLabName());
        assertEquals(LocalDate.of(2024, 8, 21), booking.getDate());
        assertEquals(LocalTime.of(14, 30), booking.getInitHour());
        assertEquals(LocalTime.of(16, 30), booking.getFinalHour());
        assertEquals("Reserva de prueba", booking.getDescription());
        assertEquals(1, booking.getPriority());
        assertEquals("user123", booking.getUserId());
    }

    /**
     * Prueba unitaria para verificar el correcto funcionamiento de los métodos setter en Booking.
     * Modifica los valores del objeto y verifica que se actualicen correctamente.
     */

    @Test
    void testSetters() {
        booking.setLabName("Lab B");
        booking.setDate(LocalDate.of(2024, 9, 1));
        booking.setInitHour(LocalTime.of(10, 0));
        booking.setFinalHour(LocalTime.of(12, 0));
        booking.setDescription("Nueva reserva");
        booking.setPriority(2);
        booking.setUserName("user456");

        assertEquals("Lab B", booking.getLabName());
        assertEquals(LocalDate.of(2024, 9, 1), booking.getDate());
        assertEquals(LocalTime.of(10, 0), booking.getInitHour());
        assertEquals(LocalTime.of(12, 0), booking.getFinalHour());
        assertEquals("Nueva reserva", booking.getDescription());
        assertEquals(2, booking.getPriority());
        assertEquals("user456", booking.getUserId());
    }
}
