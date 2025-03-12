package com.cvds.eci.laboratoryreservations.app_core.model;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "es-CO", timezone = "America/Bogota")
    private LocalDate date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", locale = "es-CO", timezone = "America/Bogota")
    private LocalTime initHour;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", locale = "es-CO", timezone = "America/Bogota")
    private LocalTime finalHour;


    private String labName;

    
    public Booking (String labName, LocalDate date, LocalTime initHour, LocalTime finalHour, String description) {
        this.labName = labName;
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

    public String getLabName(){
        return labName;
    }

    public void setLabName(String labName){
        this.labName = labName;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDate getDate() {
        return date;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }


    public LocalTime getInitHour() {
        return initHour;
    }


    public void setInitHour(LocalTime initHour) {
        this.initHour = initHour;
    }


    public LocalTime getFinalHour() {
        return finalHour;
    }


    public void setFinalHour(LocalTime finalHour) {
        this.finalHour = finalHour;
    }


    
}

