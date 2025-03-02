package com.cvds.eci.laboratoryreservations.app_core.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;

public interface BookingRepository extends MongoRepository<Booking,String> {
 /**
  * This function finds a booking by laboratory ID and initial hour.
  * 
  * @param laboratoryId The `laboratoryId` parameter is a unique identifier for a laboratory. It is
  * used to specify which laboratory you want to search for in the database.
  * @param initHour The `initHour` parameter is a `LocalDateTime` object representing the start time of
  * a booking.
  * @return An Optional object containing a Booking entity that matches the given laboratoryId and
  * initHour, or an empty Optional if no matching Booking is found.
  */
 Optional<Booking> findByLaboratoryNameAndInitHour(String laboratoryId, LocalDateTime initHour);
    

}
