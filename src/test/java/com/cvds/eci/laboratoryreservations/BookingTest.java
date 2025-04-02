package com.cvds.eci.laboratoryreservations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;
import com.cvds.eci.laboratoryreservations.app_core.model.Laboratory;
import com.cvds.eci.laboratoryreservations.app_core.repository.BookingRepository;
import com.cvds.eci.laboratoryreservations.app_core.repository.LaboratoryRepository;
import com.cvds.eci.laboratoryreservations.app_core.service.BookingService;
public class BookingTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private LaboratoryRepository laboratoryRepository;
    
     @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Dado que tengo 1 reserva registrada, Cuando lo consulto a nivel de servicio, 
     * entonces la consulta será exitosa validando el campo id
     */
    @Test
    void ShouldGetAExistingBooking(){

        Booking booking1 = new Booking("RECO", LocalDate.of(1990, 03, 28), LocalTime.of(10, 0, 0),LocalTime.of(13, 0, 0), "Reserva para redes",0,"Salomon");
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
    /**
     * Dado que no hay ninguna reserva registrada, 
     * Cuándo la consulto a nivel de servicio, 
     * Entonces la consulta no retornará ningún resultado.
     */
    @Test
    void shouldNotReturnBookingWhenThereIsNotBookings() {
        when(bookingRepository.findAll()).thenReturn(Collections.emptyList());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookingService.getAllBookings();
        });
        assertEquals("The booking's list is empty", exception.getMessage());
    }


    /**
     * Cuando consulto una reserva por su id y no existe, no me debera
     * retornar resultados
     */
    @Test
    void shouldNotReturnBookingById(){
        String id = "123456789";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookingService.findById(id);
        });
        assertEquals("The booking " + id + " does not exist", exception.getMessage());

    }

    /**
     * Dado que no hay ninguna reserva registrada, 
     * Cuándo lo creo a nivel de servicio, Entonces la creación será exitosa.
     */
    
    @Test
    void shouldCreateBookingSuccessfully() {
        Booking booking5 = new Booking("RECO",  LocalDate.of(9000, 3, 28), LocalTime.of(10, 0), LocalTime.of(13, 0),  "Reserva para redes",0, "Salomon");
        booking5.setId("1234567890");

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking5);
        Laboratory laboratory = new Laboratory("RECO", "AULA", 56, true);

        when(laboratoryRepository.findByName("RECO")).thenReturn(laboratory);

        
        Booking savedBooking = bookingService.addBooking(booking5);

        
        assertNotNull(savedBooking);
        assertEquals("1234567890", savedBooking.getId());
        assertEquals("RECO", savedBooking.getLabName());
    }


    /**
     * Dado que tengo 1 reserva registrada, 
     * Cuándo la elimino a nivel de servicio, Entonces la eliminación será exitosa.
     */

    @Test
    void shouldDeleteBookingSuccessfully() {
        String bookingId = "12345678900";
        Booking booking = new Booking("RECO", LocalDate.of(2020, 3, 12), LocalTime.of(10, 0), LocalTime.of(13, 0), "Reserva para redes",0, "Salomon");
        booking.setId(bookingId);
        
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));


        String deletedId = bookingService.deleteBooking(bookingId);

        assertEquals(bookingId, deletedId);
    }
    

}




    
    
    

    

