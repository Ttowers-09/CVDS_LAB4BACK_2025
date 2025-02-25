package com.cvds.eci.laboratoryreservations.app_core.service;

import java.util.ArrayList;
import java.util.List;

import com.cvds.eci.laboratoryreservations.app_core.model.Laboratory;

public class LaboratoryService {
    private List<Laboratory> Laboratories;

    public LaboratoryService(){
        Laboratories = new ArrayList<>();
    }

    public List<Laboratory> getLaboratories() {
        return Laboratories;
    }

    public void setLaboratories(List<Laboratory> laboratories) {
        Laboratories = laboratories;
    }

    

    public List<Laboratory> deletetLaboratories(Laboratory Laboratory){
        List<Laboratory> labs = getLaboratories();
        labs.add(Laboratory);
        return labs;
    };
}
