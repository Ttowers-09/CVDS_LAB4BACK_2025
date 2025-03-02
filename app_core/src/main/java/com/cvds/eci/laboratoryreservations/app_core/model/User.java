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
    
    /**
     * Getter id
     * @return user's id
     */
    public String getId(){
        return this.id;
    }
    /**
     * Getter name
     * @return user's name
     */
    public String getName(){
        return this.name;
    }
    /**
     * Getter email
     * @return user's email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Getter rol
     * @return user's rol
     */
    public String getRol(){
        return this.rol;
    }


    /**
     * Setter id
     * @param id
     */
    public void setId(String id){
        this.id = id;
    }
    /**
     * Setter name
     * @param name
     */
    public void setName(String name){
        this.name=name;
    }
    /**
     * Setter email
     * @param email
     */
    public void setEmail(String email){
        this.email=email;
    }

    /**
     * Setter rol
     * @param rol
     */
    public void setRol(String rol){
        this.rol=rol;
    }
    



}
