package com.cvds.eci.laboratoryreservations.app_core.model;
import java.time.LocalDateTime;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;



    private LocalDateTime date;
    private String description;

    private LocalDateTime initHour;
    
    private LocalDateTime finalHour;

    @DBRef
    private User user;

    @DBRef 
    private Laboratory lab;

    
    public Booking(User user, Laboratory lab, LocalDateTime date, LocalDateTime initHour, LocalDateTime finalHour, String description) {
        this.user = user;
        this.lab = lab;
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


    public User getUser() {
        return user;
    }


    public Laboratory getLab() {
        return lab;
    }



    
    
    


    
}

