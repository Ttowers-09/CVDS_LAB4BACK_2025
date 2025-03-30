package com.cvds.eci.laboratoryreservations.app_core.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Representa una reserva de laboratorio dentro del sistema de reservas.
 */
@Document(collection = "bookings")
public class Booking {
    
    /**
     * Identificador único de la reserva en la base de datos.
     */
    @Id
    private String id;

    /**
     * Descripción breve de la reserva.
     */
    private String description;

    /**
     * Fecha en la que se realizará la reserva.
     * Formato: dd-MM-yyyy (Ejemplo: 21-08-2024)
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "es-CO", timezone = "America/Bogota")
    private LocalDate date;

    /**
     * Hora de inicio de la reserva.
     * Formato: HH:mm (Ejemplo: 14:30)
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", locale = "es-CO", timezone = "America/Bogota")
    private LocalTime initHour;

    /**
     * Hora de finalización de la reserva.
     * Formato: HH:mm (Ejemplo: 16:30)
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", locale = "es-CO", timezone = "America/Bogota")
    private LocalTime finalHour;

    /**
     * Nombre del laboratorio reservado.
     */
    private String labName;

    /**
     * Constructor de la clase Booking.
     *
     * @param labName     Nombre del laboratorio a reservar.
     * @param date        Fecha de la reserva.
     * @param initHour    Hora de inicio de la reserva.
     * @param finalHour   Hora de finalización de la reserva.
     * @param description Descripción de la reserva.
     */
    public Booking(String labName, LocalDate date, LocalTime initHour, LocalTime finalHour, String description) {
        this.labName = labName;
        this.date = date;
        this.initHour = initHour;
        this.finalHour = finalHour;
        this.description = description;
    }

    /**
     * Obtiene el identificador único de la reserva.
     *
     * @return ID de la reserva.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único de la reserva.
     *
     * @param id Nuevo ID de la reserva.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del laboratorio reservado.
     *
     * @return Nombre del laboratorio.
     */
    public String getLabName() {
        return labName;
    }

    /**
     * Establece el nombre del laboratorio reservado.
     *
     * @param labName Nuevo nombre del laboratorio.
     */
    public void setLabName(String labName) {
        this.labName = labName;
    }

    /**
     * Obtiene la descripción de la reserva.
     *
     * @return Descripción de la reserva.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción de la reserva.
     *
     * @param description Nueva descripción de la reserva.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene la fecha de la reserva.
     *
     * @return Fecha de la reserva.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Establece la fecha de la reserva.
     *
     * @param date Nueva fecha de la reserva.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Obtiene la hora de inicio de la reserva.
     *
     * @return Hora de inicio.
     */
    public LocalTime getInitHour() {
        return initHour;
    }

    /**
     * Establece la hora de inicio de la reserva.
     *
     * @param initHour Nueva hora de inicio.
     */
    public void setInitHour(LocalTime initHour) {
        this.initHour = initHour;
    }

    /**
     * Obtiene la hora de finalización de la reserva.
     *
     * @return Hora de finalización.
     */
    public LocalTime getFinalHour() {
        return finalHour;
    }

    /**
     * Establece la hora de finalización de la reserva.
     *
     * @param finalHour Nueva hora de finalización.
     */
    public void setFinalHour(LocalTime finalHour) {
        this.finalHour = finalHour;
    }
}
