package com.cvds.eci.laboratoryreservations.app_core.ServiceTests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;
import com.cvds.eci.laboratoryreservations.app_core.model.Laboratory;
import com.cvds.eci.laboratoryreservations.app_core.repository.BookingRepository;
import com.cvds.eci.laboratoryreservations.app_core.repository.LaboratoryRepository;
import com.cvds.eci.laboratoryreservations.app_core.service.BookingService;

/**
 * Pruebas unitarias para el servicio BookingService.
 */
@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    
    @Mock
    private BookingRepository bookingRepository;
    
    @Mock
    private LaboratoryRepository laboratoryRepository;
    
    @InjectMocks
    private BookingService bookingService;
    
    private Booking booking;
    private Laboratory laboratory;
    
    @BeforeEach
    void setUp() {
        laboratory = new Laboratory("Lab A", "Edificio 1, Piso 2", 30, true);
        booking = new Booking("Lab A", LocalDate.of(2024, 8, 21), LocalTime.of(14, 30), LocalTime.of(16, 30), "Reserva para prueba", 1);
    }
    
    /**
     * Verifica que se obtengan todas las reservas correctamente.
     */
    @Test
    void testGetAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        when(bookingRepository.findAll()).thenReturn(bookings);
        
        List<Booking> result = bookingService.getAllBookings();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
    
    /**
     * Verifica que se agregue una reserva correctamente cuando no hay conflictos de horario.
     */
    @Test
    void testAddBookingSuccess() {
        when(laboratoryRepository.findByName(booking.getLabName())).thenReturn(laboratory);
        when(bookingRepository.findByLabNameAndDateAndInitHourLessThanAndFinalHourGreaterThan(
            anyString(), any(), any(), any())).thenReturn(new ArrayList<>());
        when(bookingRepository.save(booking)).thenReturn(booking);
        
        Booking savedBooking = bookingService.addBooking(booking);
        assertNotNull(savedBooking);
        assertEquals("Lab A", savedBooking.getLabName());
    }
    
    /**
     * Verifica que no se pueda agregar una reserva si hay un conflicto de horario.
     */
    @Test
    void testAddBookingConflict() {
        when(laboratoryRepository.findByName(booking.getLabName())).thenReturn(laboratory);
        List<Booking> conflicts = new ArrayList<>();
        conflicts.add(booking);
        when(bookingRepository.findByLabNameAndDateAndInitHourLessThanAndFinalHourGreaterThan(
            anyString(), any(), any(), any())).thenReturn(conflicts);
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookingService.addBooking(booking);
        });
        assertEquals("There's a laboratory booked between that schedule already.", exception.getMessage());
    }
    
    /**
     * Verifica que una reserva pueda ser eliminada exitosamente.
     */
    @Test
    void testDeleteBookingSuccess() {
        when(bookingRepository.findById("1")).thenReturn(Optional.of(booking));
        doNothing().when(bookingRepository).deleteById("1");
        
        String deletedId = bookingService.deleteBooking("1");
        assertEquals("1", deletedId);
    }
    
    /**
     * Verifica que se lance una excepción al intentar eliminar una reserva inexistente.
     */
    @Test
    void testDeleteBookingNotFound() {
        when(bookingRepository.findById("2")).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookingService.deleteBooking("2");
        });
        assertEquals("The booking 2 does not exist", exception.getMessage());
    }
    
    /**
     * Verifica que se pueda encontrar una reserva por su ID.
     */
    @Test
    void testFindByIdSuccess() {
        when(bookingRepository.findById("1")).thenReturn(Optional.of(booking));
        
        Booking foundBooking = bookingService.findById("1");
        assertNotNull(foundBooking);
        assertEquals("Lab A", foundBooking.getLabName());
    }
    
    /**
     * Verifica que se lance una excepción al intentar encontrar una reserva inexistente.
     */
    @Test
    void testFindByIdNotFound() {
        when(bookingRepository.findById("2")).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookingService.findById("2");
        });
        assertEquals("The booking 2 does not exist", exception.getMessage());
    }
}
