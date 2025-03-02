package com.cvds.eci.laboratoryreservations.app_core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvds.eci.laboratoryreservations.app_core.model.Laboratory;
import com.cvds.eci.laboratoryreservations.app_core.service.LaboratoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class LaboratoryController {
    private final LaboratoryService labService;

    public LaboratoryController(LaboratoryService labService) {
        this.labService = labService;
    }

    
    /**
     * This Java function returns a list of laboratories as a ResponseEntity.
     * 
     * @return A list of Laboratory objects is being returned in the ResponseEntity body.
     */
    @GetMapping("/labs")
    public ResponseEntity<?> getLaboratories(){
        List<Laboratory> laboratories = labService.getLaboratories();
        return ResponseEntity.ok(laboratories);

    }

    /**
     * This Java function adds a laboratory entity to the system and returns a response indicating
     * successful insertion.
     * 
     * @param laboratory The `addLaboratory` method in the code snippet is a POST mapping that adds a
     * new laboratory to the system. The method takes a `Laboratory` object as a request body and then
     * calls the `addLaboratory` method from the `labService` to add the laboratory to the system
     * @return A ResponseEntity object with a status code of 201 (Created) and a body containing a Map
     * with a "response" key and the value "Laboratory Insert OK".
     */
    @PostMapping("/labs")
    public ResponseEntity<?> addLaboratory(@RequestBody Laboratory laboratory) { // @RequestBody Convierte automáticamente el JSON del cuerpo de la petición en un objeto Java.
        labService.addLaboratory(laboratory); // Guarda y obtiene el ID

        Map<String, String> response = new HashMap<String, String>();
        response.put("response", "Laboratory Insert OK");
        return ResponseEntity.status(201).body(response);

    }

    @DeleteMapping("/labs/{id}")
    public ResponseEntity<?> deleteLaboratory(@PathVariable String id){
        labService.deleteLaboratory(id);
        Map<String, String> response = new HashMap<String, String>();
        response.put("response", "Laboratory: " + id  + " Deleted OK");
        return ResponseEntity.ok(response);
    }

    
    
}
