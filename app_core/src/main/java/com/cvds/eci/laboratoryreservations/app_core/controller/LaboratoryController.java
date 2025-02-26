package com.cvds.eci.laboratoryreservations.app_core.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/labs")
    public List<Laboratory> getLaboratories(){
        return labService.getLaboratories();
    }

    @PostMapping("/labs")
    public void addLaboratory(@RequestBody Laboratory laboratory) {
        labService.addLaboratory(laboratory);
    }
    
}
