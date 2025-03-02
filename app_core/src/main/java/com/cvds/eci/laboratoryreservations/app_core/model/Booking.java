package com.cvds.eci.laboratoryreservations.app_core.model;
import java.time.LocalDateTime;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;
    private String userId;
    private String laboratoryName;
    private LocalDateTime date;
    private String description;
    private LocalDateTime initHour;
    private LocalDateTime finalHour;
    
    public Booking(String userId, String laboratoryName, LocalDateTime date, LocalDateTime initHour, LocalDateTime finalHour, String description) {
        this.userId = userId;
        this.laboratoryName = laboratoryName;
        this.date = date;
        this.initHour = initHour;
        this.finalHour = finalHour;
        this.description = description;
    }


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


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    
    
    


    
}

