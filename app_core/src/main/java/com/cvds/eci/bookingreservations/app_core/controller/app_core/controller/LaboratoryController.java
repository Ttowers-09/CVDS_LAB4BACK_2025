package com.cvds.eci.bookingreservations.app_core.controller.app_core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvds.eci.bookingreservations.app_core.controller.app_core.model.Laboratory;
import com.cvds.eci.bookingreservations.app_core.controller.app_core.service.LaboratoryService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@CrossOrigin(origins = "http://localhost:3000") conexión a react

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
    @CrossOrigin(origins = "http://localhost:3000")
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
        laboratory.setId(String.valueOf(System.currentTimeMillis())); // ID temporal
        Map<String, String> response = new HashMap<String, String>();
        response.put("response", "Laboratory Insert OK");
        return ResponseEntity.status(201).body(response);

    }

    @DeleteMapping("/labs/{id}")
    public ResponseEntity<?> deleteLaboratory(@PathVariable String id){
        Map<String, String> response = new HashMap<String, String>();
        response.put("response", "Laboratory: " + id  + " Deleted OK");
        return ResponseEntity.ok().body(response);
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
    @PutMapping("/labs/{id}")
    public ResponseEntity<?> updateLaboratoryName(@PathVariable String id, @RequestBody Laboratory updatedLab) {
        labService.updateLaboratory(id, updatedLab);
        return ResponseEntity.ok().body("Laboratory updated OK");
    }
    
    
}
