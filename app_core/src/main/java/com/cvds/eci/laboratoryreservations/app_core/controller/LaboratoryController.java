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

@RestController
@RequestMapping("/api/labs")
@CrossOrigin(origins = "http://localhost:3000")
public class LaboratoryController {
    private final LaboratoryService labService;

    public LaboratoryController(LaboratoryService labService) {
        this.labService = labService;
    }

    /**
     * Obtiene la lista de todos los laboratorios disponibles.
     * 
     * @return ResponseEntity con la lista de laboratorios en caso de éxito o un mensaje de error en caso de fallo.
     */
    @GetMapping
    public ResponseEntity<?> getLaboratories() {
        try {
            List<Laboratory> laboratories = labService.getLaboratories();
            return ResponseEntity.ok(laboratories);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Ha ocurrido un error al listar los laboratorios :("));
        }
    }

    /**
     * Agrega un nuevo laboratorio al sistema.
     *
     * @param laboratory Objeto Laboratory que se desea agregar, recibido en el cuerpo de la solicitud.
     * @return ResponseEntity con un mensaje de confirmación o un mensaje de error en caso de fallo.
     */
    @PostMapping
    public ResponseEntity<?> addLaboratory(@RequestBody Laboratory laboratory) {
        try {
            labService.addLaboratory(laboratory);
            return ResponseEntity.status(201).body(Collections.singletonMap("response", "Laboratory Insert OK"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", e));
        }
    }

    /**
     * Elimina un laboratorio específico por su identificador.
     *
     * @param id Identificador único del laboratorio que se desea eliminar.
     * @return ResponseEntity con un mensaje de confirmación o un mensaje de error en caso de fallo.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLaboratory(@PathVariable String id) {
        try {
            labService.deleteLaboratory(id);
            return ResponseEntity.status(200).body(Collections.singletonMap("response", "Laboratory: " + id + " Deleted OK :)"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Ha ocurrido un error al intentar eliminar el laboratorio :("));
        }
    }

    /**
     * Actualiza la información de un laboratorio específico.
     *
     * @param id Identificador único del laboratorio que se desea actualizar.
     * @param updatedLab Objeto Laboratory con los datos actualizados.
     * @return ResponseEntity con un mensaje de confirmación o un mensaje de error en caso de fallo.
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
