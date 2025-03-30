package com.cvds.eci.laboratoryreservations.app_core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa un usuario dentro del sistema de reservas de laboratorios.
 */
@Document(collection = "users")
public class User {

    /**
     * Identificador único del usuario en la base de datos.
     */
    @Id
    private String id;

    /**
     * Nombre del usuario.
     */
    private String name;

    /**
     * Correo electrónico del usuario.
     */
    private String email;

    /**
     * Rol del usuario dentro del sistema (ejemplo: administrador, estudiante, profesor).
     */
    private String rol;

    /**
     * Contraseña del usuario.
     */
    private String password;

    /**
     * Constructor de la clase User.
     *
     * @param name     Nombre del usuario.
     * @param email    Correo electrónico del usuario.
     * @param rol      Rol del usuario en el sistema.
     * @param password Contraseña del usuario.
     */
    public User(String name, String email, String rol, String password) {
        this.name = name;
        this.email = email;
        this.rol = rol;
        this.password = password;
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return ID del usuario.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id Nuevo ID del usuario.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return Nombre del usuario.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param name Nuevo nombre del usuario.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return Correo electrónico del usuario.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email Nuevo correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el rol del usuario en el sistema.
     *
     * @return Rol del usuario.
     */
    public String getRol() {
        return this.rol;
    }

    /**
     * Establece el rol del usuario en el sistema.
     *
     * @param rol Nuevo rol del usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password Nueva contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
