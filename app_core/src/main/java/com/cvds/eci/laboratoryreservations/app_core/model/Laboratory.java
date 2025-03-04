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
    private boolean available;


	public Laboratory(String name, String location,int capacity,boolean available) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.available = available;
    }
    
    @Override
    public String toString() {
        return "Laboratory{" +
					   "nombre='" + name + '\'' +
					   ", ubicación='" + location + '\'' +
					   ", capacidad=" + capacity +
					   '}' + "available: " + available;
    }
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	


}
