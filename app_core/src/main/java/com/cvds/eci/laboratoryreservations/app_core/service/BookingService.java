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

    
    private UserService userService;

    @Autowired
    private LaboratoryRepository laboratoryRepository;
    public List<Booking> getAllBookings() {
        List<Booking> list = bookingRepository.findAll();
        if (list.isEmpty()){
            throw new RuntimeException("The list is empty");
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

        List<Booking> conflictBookings = bookingRepository.findBylabNameAndInitHourLessThanAndFinalHourGreaterThan(
            lab.getName(),
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
            throw new RuntimeException("No Existe la reserva a eliminar");
        }
        bookingRepository.deleteById(id);
        return id;
    }

    public Booking findById(String id){
        Booking bookingSearch = bookingRepository.findById(id).orElse(null);
        if(bookingSearch == null){
            throw new RuntimeException("No Existe la reserva");
        }
        return bookingSearch;
    }
}