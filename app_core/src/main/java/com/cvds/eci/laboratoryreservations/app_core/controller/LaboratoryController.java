package com.cvds.eci.laboratoryreservations.app_core.controller;


import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvds.eci.laboratoryreservations.app_core.model.Laboratory;
import com.cvds.eci.laboratoryreservations.app_core.service.LaboratoryService;

//@CrossOrigin(origins = "http://localhost:3000") conexión a react

@RestController
@RequestMapping("/api/labs")
@CrossOrigin(origins = "http://localhost:300")
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
    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<?> getLaboratories(){
        try{
            List<Laboratory> laboratories = labService.getLaboratories();
            return ResponseEntity.ok(laboratories);
        }catch(RuntimeException e){
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Ha ocurrido un error al listar los labotatorio :("));
        }

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
    @PostMapping
    public ResponseEntity<?> addLaboratory(@RequestBody Laboratory laboratory) { // @RequestBody Convierte automáticamente el JSON del cuerpo de la petición en un objeto Java.
        try {
            labService.addLaboratory(laboratory); // Guarda y obtiene el ID
            return ResponseEntity.status(201).body(Collections.singletonMap("response", " Laboratory Insert OK"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e ));
        }
        

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLaboratory(@PathVariable String id) {
        try {
            labService.deleteLaboratory(id);
            return ResponseEntity.status(200).body(Collections.singletonMap("response", "Laboratory: " + id + " Deleted OK :)"));
        }catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Ha ocurrido un error al intentar eliminar el laboratorio :("));
        }
    }

        
        

    /**
     * This Java function updates the name of a laboratory using the provided ID and new name in the
     * request body.
     * 
     * @param id The `id` parameter in the `@PutMapping` annotation represents the unique identifier of
     * the laboratory that you want to update. It is extracted from the URL path of the request.
     * @param body The `body` parameter in the `updateLaboratoryName` method is a `Map<String, String>`
     * representing the request body of the PUT request. It is expected to contain key-value pairs
     * where the key is a string and the value is also a string. In this case, it is used
     * @return The method is returning a ResponseEntity with a message "Laboratory updated
     * successfully" in the response body.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLaboratoryName(@PathVariable String id, @RequestBody Laboratory updatedLab) {
        try {
            labService.updateLaboratory(id, updatedLab);
            return ResponseEntity.ok().body("Laboratory updated OK");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Ha ocurrido un error al intentar actualizar el laboratorio :("));
        }
        
    }
    
    
}
