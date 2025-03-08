package com.cvds.eci.laboratoryreservations.app_core.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "bookings")
public class Booking {
    // The `@Id` annotation in Spring Data MongoDB is used to mark a field as the primary identifier of
    // a document in a MongoDB collection. In the context of the `Booking` class, the `@Id` annotation
    // is applied to the `id` field, indicating that this field serves as the unique identifier for
    // each `Booking` object when stored in the MongoDB database. This annotation tells the Spring Data
    // MongoDB framework that the `id` field should be used as the primary key for identifying and
    // retrieving `Booking` objects.
    @Id
    private String id;

   
    private String description;

     // Devuelve solo la fecha1970-01-01T12:00:00Z"
    private LocalDateTime date;

     // Devuelve solo la hora
    private LocalDateTime initHour;


    private LocalDateTime finalHour;

    // The `@DBRef` annotation in Spring Data MongoDB is used to establish a reference between two
    // documents in different collections. In the context of the `Booking` class, the `@DBRef`
    // annotation is used to create a reference to a `Laboratory` object. This allows you to establish
    // a relationship between a booking and a laboratory without embedding the laboratory document
    // within the booking document. When you retrieve a `Booking` object, the `@DBRef` annotation will
    // automatically fetch the associated `Laboratory` object based on the reference.
    //@DBRef 
    //private Laboratory lab;

    private String labName;

    private int priority;

    
    public Booking (String labName, LocalDateTime date, LocalDateTime initHour, LocalDateTime finalHour, String description, int priority) {
        this.labName = labName;
        this.date = date;
        this.initHour = initHour;
        this.finalHour = finalHour;
        this.description = description;
        this.priority = priority;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabName(String labName){
        this.labName = labName;
    }

    public String getLabName(){
        return labName;
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
    
}

