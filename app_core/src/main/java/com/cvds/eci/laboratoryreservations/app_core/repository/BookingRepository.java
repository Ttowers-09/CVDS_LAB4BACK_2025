package com.cvds.eci.laboratoryreservations.app_core.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;

public interface BookingRepository extends MongoRepository<Booking,String> {
 
  List<Booking> findByLaboratoryNameAndInitHourLessThanAndFinalHourGreaterThan(
    String laboratoryName, LocalDateTime finalHour, LocalDateTime initHour);}
