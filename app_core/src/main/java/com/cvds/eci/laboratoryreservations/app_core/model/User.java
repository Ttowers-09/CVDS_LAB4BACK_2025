package com.cvds.eci.laboratoryreservations.app_core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class User {
    
    @Id
    private String id;
    private String name;
    private String email;
    private String rol;

    public User(String name, String email, String rol) {
        this.name = name;
        this.email = email;
        this.rol = rol;
    }

}
