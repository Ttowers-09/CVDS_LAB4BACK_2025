package com.cvds.eci.bookingreservations.app_core.controller.app_core.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cvds.eci.bookingreservations.app_core.controller.app_core.model.Booking;

public interface BookingRepository extends MongoRepository<Booking,String> {

    

}
