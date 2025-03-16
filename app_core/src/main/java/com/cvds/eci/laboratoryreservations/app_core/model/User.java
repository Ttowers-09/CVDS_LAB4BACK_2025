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
    private String password;

    public User(String name, String email, String rol, String password) {
        this.name = name;
        this.email = email;
        this.rol = rol;
        this.password = password;
    }
    

  
    public String getId(){
        return this.id;
    }
   
    public String getName(){
        return this.name;
    }
    
    public String getEmail(){
        return this.email;
    }

    
    public String getRol(){
        return this.rol;
    }


    public void setId(String id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name=name;
    }
   
    public void setEmail(String email){
        this.email=email;
    }

    public void setRol(String rol){
        this.rol=rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
