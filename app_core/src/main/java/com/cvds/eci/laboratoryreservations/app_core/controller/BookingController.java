package com.cvds.eci.laboratoryreservations.app_core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;
import com.cvds.eci.laboratoryreservations.app_core.service.BookingService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    
    /**
     * This Java function returns a list of bookings as a ResponseEntity.
     * 
     * @return A list of bookings objects is being returned in the ResponseEntity body.
     */
    @GetMapping("/bookings")
    public ResponseEntity<?> getBookings(){
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);

    }

    /**
     * This Java function adds a booking entity to the system and returns a response indicating
     * successful insertion.
     * 
     * @param booking The `addBooking` method in the code snippet is a POST mapping that adds a
     * new booking to the system. The method takes a `Booking` object as a request body and then
     * calls the `addBooking` method from the `labService` to add the booking to the system
     * @return A ResponseEntity object with a status code of 201 (Created) and a body containing a Map
     * with a "response" key and the value "booking Insert OK".
     */
    @PostMapping("/bookings")
    public ResponseEntity<?> addBooking(@RequestBody Booking booking) { // @RequestBody Convierte automáticamente el JSON del cuerpo de la petición en un objeto Java.
        bookingService.addBooking(booking); // Guarda y obtiene el ID

        Map<String, String> response = new HashMap<String, String>();
        response.put("response", "Booking Insert OK");
        return ResponseEntity.status(201).body(response);

    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable String id){
        bookingService.deleteBooking(id);
        Map<String, String> response = new HashMap<String, String>();
        response.put("response", "booking: " + id  + " Deleted OK");
        return ResponseEntity.ok(response);
    }

    
    
}
