package com.cvds.eci.laboratoryreservations.app_core.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;
    private String userId;
    private String laboratoryId;
    private LocalDateTime date;
    private LocalDateTime initHour;
    private LocalDateTime finalHour;
    private String description;

    public Booking(String userId, String laboratoryId, LocalDateTime date, String description) {
        this.userId = userId;
        this.laboratoryId = laboratoryId;
        this.date = date;
        this.description = description;
    }
}

