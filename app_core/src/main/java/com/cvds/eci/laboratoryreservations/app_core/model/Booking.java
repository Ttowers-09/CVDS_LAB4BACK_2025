package com.cvds.eci.laboratoryreservations.app_core.model;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(String laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getInitHour() {
        return initHour;
    }

    public void setInitHour(LocalDateTime initHour) {
        this.initHour = initHour;
    }

    public LocalDateTime getFinalHour() {
        return finalHour;
    }

    public void setFinalHour(LocalDateTime finalHour) {
        this.finalHour = finalHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Booking(String userId, String laboratoryId, LocalDateTime date, String description) {
        this.userId = userId;
        this.laboratoryId = laboratoryId;
        this.date = date;
        this.description = description;
    }
    





    
}

