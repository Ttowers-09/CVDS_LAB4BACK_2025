package com.cvds.eci.laboratoryreservations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;
import com.cvds.eci.laboratoryreservations.app_core.repository.BookingRepository;
import com.cvds.eci.laboratoryreservations.app_core.service.BookingService;
public class BookingTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

     @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ShouldGetAExistingBooking(){

        Booking booking1 = new Booking("RECO", LocalDate.of(1990, 03, 28), LocalTime.of(10, 0, 0),LocalTime.of(13, 0, 0), "Reserva para redes");
        booking1.setId("12345");

        //Configurar Mockito para simular respuestas
        List<Booking> mockBookings = Arrays.asList(booking1);
        when(bookingRepository.findAll()).thenReturn(mockBookings);
        when(bookingRepository.findById("12345")).thenReturn(Optional.of(booking1));

        List<Booking> bookings = bookingService.getAllBookings();
        // Verificar que hay al menos una reserva
        Booking booking = bookings.get(0);
        String id = booking.getId();

        // Comparar con la reserva obtenida del servicio
        assertEquals(booking, bookingService.findById(id));
    }
}
