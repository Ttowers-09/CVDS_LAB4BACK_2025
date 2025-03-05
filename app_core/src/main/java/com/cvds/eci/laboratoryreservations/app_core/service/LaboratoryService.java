package com.cvds.eci.laboratoryreservations.app_core.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvds.eci.laboratoryreservations.app_core.model.Laboratory;
import com.cvds.eci.laboratoryreservations.app_core.repository.LaboratoryRepository;
@Service
public class LaboratoryService {
    
    // `@Autowired` is an annotation in Spring Framework that marks a constructor, field, setter
    // method, or configuration method to be autowired by Spring's dependency injection capabilities.
    // In the context of the code snippet you provided, `@Autowired` is used to inject an instance of
    // `LaboratoryRepository` into the `LaboratoryService` class, allowing the service to interact with
    // the repository without explicitly creating an instance of it.
    @Autowired
    private LaboratoryRepository labRepository;


   /**
    * The function `getLaboratories()` returns a list of all laboratories from the labRepository.
    * 
    * @return A list of Laboratory objects is being returned.
    */
    public List<Laboratory> getLaboratories() {

        List<Laboratory> list = labRepository.findAll();
        if (list.isEmpty()){
            throw new RuntimeException("The list is empty");
        }
        return list;
    }
    
    /**
     * The function adds a laboratory to the lab repository by saving it.
     * 
     * @param laboratory The `addLaboratory` method takes a `Laboratory` object as a parameter and
     * saves it using the `labRepository`.
     */
    public Laboratory addLaboratory(Laboratory laboratory) {
        Laboratory existingLab = labRepository.findByName(laboratory.getName());
        if (existingLab != null) {
            throw new RuntimeException("El laboratorio con nombre " + laboratory.getName() + " ya existe.");
        }
        return labRepository.save(laboratory);
    }
    

    /**
     * The `deleteLaboratory` function deletes a laboratory record from the repository based on the
     * provided laboratory ID.
     * 
     * @param idLaboratory The `idLaboratory` parameter is a unique identifier that is used to specify
     * which laboratory record should be deleted from the database.
     */
    public void deleteLaboratory(String id){
        Laboratory lab = labRepository.findById(id).orElse(null);
        if(lab == null){
            throw new RuntimeException("No Existe el laboratorio a eliminar");
        }
        labRepository.deleteById(id);
    }

 

    /**
     * The `updateLaboratory` function updates a laboratory entity in the repository based on the
     * provided ID with the information from the updatedLab object.
     * 
     * @param idLaboratory The `idLaboratory` parameter is the unique identifier of the laboratory that
     * you want to update. It is used to retrieve the specific laboratory entity from the repository
     * for updating.
     * @param updatedLab `updatedLab` is an object of type `Laboratory` that contains the updated
     * information for a laboratory entity. It may include the new name, location, and capacity for the
     * laboratory that needs to be updated in the repository.
     */
    public void updateLaboratory(String idLaboratory,Laboratory updatedLab) {
        // The line `Optional<Laboratory> labOptional = labRepository.findById(idLaboratory);` is
        // retrieving a laboratory entity from the repository based on the provided `idLaboratory`.
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
