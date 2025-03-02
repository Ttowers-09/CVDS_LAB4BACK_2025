package com.cvds.eci.laboratoryreservations.app_core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;
import com.cvds.eci.laboratoryreservations.app_core.repository.BookingRepository;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }


    /**
     * The `addBooking` function checks if a booking already exists for a laboratory at a specific hour
     * and saves the booking if it does not exist.
     * 
     * @param booking Booking object containing information about a laboratory reservation, such as
     * laboratory ID and initial hour.
     * @return The `addBooking` method returns a `Booking` object.
     */
    public Booking addBooking(Booking booking) {
        Optional<Booking> existingBooking = bookingRepository.findByLaboratoryNameAndInitHour(
            booking.getLaboratoryName(), booking.getInitHour()
        );
        
        if (existingBooking.isPresent()) {
            throw new RuntimeException("Reservation already exists");
        }
    
        return bookingRepository.save(booking);
    }

    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }
}
