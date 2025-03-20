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
//prueba
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
    @GetMapping
    public ResponseEntity<?> getBookings(){
        try{
            List<Booking> bookings = bookingService.getAllBookings();
            return ResponseEntity.ok(bookings);  
        }catch(RuntimeException e){
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e.getMessage()));
        }
        
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
    @PostMapping
    public ResponseEntity<?> addBooking(@RequestBody Booking booking) { // @RequestBody Convierte automáticamente el JSON del cuerpo de la petición en un objeto Java.
        try{
            Booking savedBooking = bookingService.addBooking(booking); // Guarda y obtiene el ID
            return ResponseEntity.status(201).body(savedBooking);
        }catch(RuntimeException e){
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable String id){
        try{
            String bookingForDelete = bookingService.deleteBooking(id);
            return ResponseEntity.status(200).body(Collections.singletonMap("response", "booking: " + bookingForDelete  + " Delete OK"));
        } catch(RuntimeException e ){
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e.getMessage()));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable String id){
        try{
            Booking bukingSearch = bookingService.findById(id);
            return ResponseEntity.status(200).body(bukingSearch);
        }catch(RuntimeException e){
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e.getMessage()));
        }
        

    }
    
}
