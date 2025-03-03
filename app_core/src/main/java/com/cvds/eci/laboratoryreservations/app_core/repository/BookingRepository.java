package com.cvds.eci.laboratoryreservations.app_core.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cvds.eci.laboratoryreservations.app_core.model.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {
    /**
     * This function retrieves bookings based on user ID, lab ID, initial hour, and final hour
     * criteria.
     * 
     * @param userId The `userId` parameter is a `String` representing the unique identifier of a user.
     * @param labId The labId parameter is a String value that represents the unique identifier of a
     * laboratory or a specific location where a booking can be made.
     * @param finalHour The `finalHour` parameter is a `LocalDateTime` object representing the end time
     * of a booking.
     * @param initHour The `initHour` parameter represents the starting time of a booking slot. It is
     * of type `LocalDateTime`, which includes both date and time information. This parameter is used
     * in the method to filter bookings based on the condition that the initial hour of the booking is
     * less than the specified `initHour
     * @return This method is returning a list of Booking objects that match the specified criteria:
     * 1. The Booking must have a userId that matches the provided userId.
     * 2. The Booking must have a labId that matches the provided labId.
     * 3. The Booking's initial hour must be less than the provided finalHour.
     * 4. The Booking's final hour must be greater than the provided initHour.
     */
    List<Booking> findByUserIdAndLabIdAndInitHourLessThanAndFinalHourGreaterThan(
        String userId, String labId, LocalDateTime finalHour, LocalDateTime initHour
    );
}
