package com.cvds.eci.laboratoryreservations.app_core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;
import com.cvds.eci.laboratoryreservations.app_core.repository.BookingRepository;
    
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }


    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }
}
