package com.cvds.eci.laboratoryreservations.app_core.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;
import com.cvds.eci.laboratoryreservations.app_core.model.Laboratory;
import com.cvds.eci.laboratoryreservations.app_core.repository.BookingRepository;
import com.cvds.eci.laboratoryreservations.app_core.repository.LaboratoryRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    

    @Autowired
    private LaboratoryRepository laboratoryRepository;
    public List<Booking> getAllBookings() {
        List<Booking> list = bookingRepository.findAll();
        if (list.isEmpty()){
            throw new RuntimeException("The booking's list is empty");
        }
        return list;
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
        Laboratory lab = laboratoryRepository.findByName(booking.getLabName());
        
        if (lab == null) {
            throw new RuntimeException("Laboratory not founded.");
        }

        List<Booking> conflictBookings = bookingRepository.findByLabNameAndDateAndInitHourLessThanAndFinalHourGreaterThan(
            lab.getName(),
            booking.getDate(),
            booking.getFinalHour(),
            booking.getInitHour()
            
        );


        if (!conflictBookings.isEmpty()) {
            throw new RuntimeException("There's a laboratory booked between that schedule already.");
        }

        return bookingRepository.save(booking);
    }

    

    public String deleteBooking(String id) {
        Booking bookingSearch = bookingRepository.findById(id).orElse(null);
        if(bookingSearch == null){
            throw new RuntimeException("The booking " + id + " does not exist");
        }
        bookingRepository.deleteById(id);
        return id;
    }

    public Booking findById(String id){
        Booking bookingSearch = bookingRepository.findById(id).orElse(null);
        if(bookingSearch == null){
            throw new RuntimeException("The booking " + id + " does not exist");
        }
        return bookingSearch;
    }



    public BookingRepository getBookingRepository() {
        return bookingRepository;
    }

    
}