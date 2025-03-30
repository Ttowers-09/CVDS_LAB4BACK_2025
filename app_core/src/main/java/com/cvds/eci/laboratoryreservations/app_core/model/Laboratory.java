package com.cvds.eci.laboratoryreservations.app_core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa un laboratorio dentro del sistema de reservas.
 */
@Document(collection = "laboratories")
public class Laboratory {

    /**
     * Identificador único del laboratorio en la base de datos.
     */
    @Id
    private String id;

    /**
     * Nombre del laboratorio.
     */
    private String name;

    /**
     * Ubicación del laboratorio dentro de la institución.
     */
    private String location;

    /**
     * Capacidad máxima de personas que puede albergar el laboratorio.
     */
    private int capacity;

    /**
     * Indica si el laboratorio está disponible para reservas.
     */
    private boolean available;

    /**
     * Constructor de la clase Laboratory.
     *
     * @param name     Nombre del laboratorio.
     * @param location Ubicación del laboratorio.
     * @param capacity Capacidad máxima del laboratorio.
     * @param available Estado de disponibilidad del laboratorio.
     */
    public Laboratory(String name, String location, int capacity, boolean available) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.available = available;
    }

    /**
     * Retorna una representación en cadena del laboratorio.
     *
     * @return Cadena con la información del laboratorio.
     */
    @Override
    public String toString() {
        return "Laboratory{" +
               "nombre='" + name + '\'' +
               ", ubicación='" + location + '\'' +
               ", capacidad=" + capacity +
               ", disponible=" + available +
               '}';
    }

    /**
     * Obtiene el identificador único del laboratorio.
     *
     * @return ID del laboratorio.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador único del laboratorio.
     *
     * @param id Nuevo ID del laboratorio.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del laboratorio.
     *
     * @return Nombre del laboratorio.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del laboratorio.
     *
     * @param name Nuevo nombre del laboratorio.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la ubicación del laboratorio.
     *
     * @return Ubicación del laboratorio.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Establece la ubicación del laboratorio.
     *
     * @param location Nueva ubicación del laboratorio.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Obtiene la capacidad máxima del laboratorio.
     *
     * @return Capacidad del laboratorio.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Establece la capacidad máxima del laboratorio.
     *
     * @param capacity Nueva capacidad del laboratorio.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Verifica si el laboratorio está disponible para reservas.
     *
     * @return `true` si está disponible, `false` en caso contrario.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Establece la disponibilidad del laboratorio.
     *
     * @param available `true` si el laboratorio está disponible, `false` si no lo está.
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
