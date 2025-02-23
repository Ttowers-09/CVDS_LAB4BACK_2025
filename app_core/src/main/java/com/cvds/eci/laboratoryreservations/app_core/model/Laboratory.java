package com.cvds.eci.laboratoryreservations.app_core.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "laboratories") 
public class Laboratory {
    
    @Id
    private String id;
    private String name;
    private String location;
    private int capacity;
    private boolean avaliable;


    public Laboratory(String name, String location,int capacity,boolean avaliable) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.avaliable = true;
    }


}
