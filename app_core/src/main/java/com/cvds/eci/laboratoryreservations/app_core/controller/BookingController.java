package com.cvds.eci.laboratoryreservations.app_core.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;
import com.cvds.eci.laboratoryreservations.app_core.service.BookingService;


@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:300")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Obtiene la lista de todas las reservas disponibles.
     * 
     * @return ResponseEntity con la lista de reservas en caso de éxito o un mensaje de error en caso de fallo.
     */
    @GetMapping
    public ResponseEntity<?> getBookings() {
        try {
            List<Booking> bookings = bookingService.getAllBookings();
            return ResponseEntity.ok(bookings);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    /**
     * Crea una nueva reserva en el sistema.
     *
     * @param booking Objeto Booking que se desea agregar, recibido en el cuerpo de la solicitud.
     * @return ResponseEntity con la reserva creada en caso de éxito o un mensaje de error en caso de fallo.
     */
    @PostMapping
    public ResponseEntity<?> addBooking(@RequestBody Booking booking) {
        try {
            Booking savedBooking = bookingService.addBooking(booking);
            return ResponseEntity.status(201).body(savedBooking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    /**
     * Elimina una reserva específica por su identificador.
     *
     * @param id Identificador único de la reserva que se desea eliminar.
     * @return ResponseEntity con un mensaje de confirmación o un mensaje de error en caso de fallo.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable String id) {
        try {
            String bookingForDelete = bookingService.deleteBooking(id);
            return ResponseEntity.status(200).body(Collections.singletonMap("response", "booking: " + bookingForDelete + " Delete OK"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    /**
     * Obtiene una reserva específica por su identificador.
     *
     * @param id Identificador único de la reserva que se desea consultar.
     * @return ResponseEntity con la reserva encontrada o un mensaje de error en caso de fallo.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable String id) {
        try {
            Booking bookingSearch = bookingService.findById(id);
            return ResponseEntity.status(200).body(bookingSearch);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?>  getBookingbyUserId(@PathVariable String id) {
        try{
            List<Booking> booking = bookingService.findAllBookingsByUserId(id);
            return ResponseEntity.status(200).body(booking);
        }
        
        catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
    
}