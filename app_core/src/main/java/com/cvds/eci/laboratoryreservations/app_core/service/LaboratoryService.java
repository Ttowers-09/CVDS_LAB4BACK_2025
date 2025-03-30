package com.cvds.eci.laboratoryreservations.app_core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvds.eci.laboratoryreservations.app_core.model.Laboratory;
import com.cvds.eci.laboratoryreservations.app_core.repository.LaboratoryRepository;

/**
 * Servicio para la gestión de laboratorios en la aplicación.
 * Proporciona métodos para obtener, agregar, eliminar y actualizar laboratorios.
 */
@Service
public class LaboratoryService {
    
    @Autowired
    private LaboratoryRepository labRepository;

    /**
     * Obtiene la lista de todos los laboratorios registrados en el sistema.
     * 
     * @return Lista de laboratorios disponibles.
     * @throws RuntimeException Si no hay laboratorios en el sistema.
     */
    public List<Laboratory> getLaboratories() {
        List<Laboratory> list = labRepository.findAll();
        if (list.isEmpty()) {
            throw new RuntimeException("The list is empty");
        }
        return list;
    }
    
    /**
     * Agrega un nuevo laboratorio al sistema.
     * 
     * @param laboratory Laboratorio a agregar.
     * @return El laboratorio guardado.
     * @throws RuntimeException Si el laboratorio ya existe.
     */
    public Laboratory addLaboratory(Laboratory laboratory) {
        Laboratory existingLab = labRepository.findByName(laboratory.getName());
        if (existingLab != null) {
            throw new RuntimeException("Laboratory " + laboratory.getName() + " exists already.");
        }
        return labRepository.save(laboratory);
    }
    
    /**
     * Elimina un laboratorio del sistema según su identificador.
     * 
     * @param id Identificador del laboratorio a eliminar.
     * @throws RuntimeException Si el laboratorio no existe.
     */
    public void deleteLaboratory(String id) {
        Laboratory lab = labRepository.findById(id).orElse(null);
        if (lab == null) {
            throw new RuntimeException("No Existe el laboratorio a eliminar");
        }
        labRepository.deleteById(id);
    }

    /**
     * Actualiza la información de un laboratorio existente en el sistema.
     * 
     * @param idLaboratory Identificador del laboratorio a actualizar.
     * @param updatedLab Objeto con los datos actualizados.
     * @throws RuntimeException Si el laboratorio no se encuentra.
     */
    public void updateLaboratory(String idLaboratory, Laboratory updatedLab) {
        Optional<Laboratory> labOptional = labRepository.findById(idLaboratory);

        if (labOptional.isPresent()) {
            Laboratory lab = labOptional.get();
            if (updatedLab.getName() != null) lab.setName(updatedLab.getName());
            if (updatedLab.getLocation() != null) lab.setLocation(updatedLab.getLocation());
            if (updatedLab.getCapacity() != 0) lab.setCapacity(updatedLab.getCapacity());
            lab.setAvailable(updatedLab.isAvailable());
            labRepository.save(lab);
        } else {
            throw new RuntimeException("Laboratory with ID " + idLaboratory + " not found");
        }
    }
}
