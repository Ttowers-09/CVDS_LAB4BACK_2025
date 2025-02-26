package com.cvds.eci.laboratoryreservations.app_core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cvds.eci.laboratoryreservations.app_core.model.Laboratory;
@Service
public class LaboratoryService {
    private List<Laboratory> laboratories;

    public LaboratoryService(){
        laboratories = new ArrayList<>();
    }

    public List<Laboratory> getLaboratories() {
        return laboratories;
    }

    public void setLaboratories(List<Laboratory> labs) {
        laboratories = labs;
    }

    /**
     * The function adds a new Laboratory object to a list of Laboratory objects and returns the
     * updated list.
     * 
     * @param laboratory  `addLaboratory` method is designed to add a new `Laboratory`
     * object to a list of laboratories and return the updated list. 
     * @return returns a List of Laboratory objects after adding a new
     * Laboratory object to the existing list of laboratories.
     */
    public void addLaboratory(Laboratory laboratory){
        laboratories.add(laboratory);
    }
    
    

    
}
