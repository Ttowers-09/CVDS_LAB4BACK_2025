package com.cvds.eci.laboratoryreservations.app_core.service;

import java.util.ArrayList;
import java.util.List;

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
        return labRepository.findAll();
    }
    
    /**
     * The function adds a laboratory to the lab repository by saving it.
     * 
     * @param laboratory The `addLaboratory` method takes a `Laboratory` object as a parameter and
     * saves it using the `labRepository`.
     */
    public Laboratory addLaboratory(Laboratory laboratory){
        return labRepository.save(laboratory);
    }

    /**
     * The `deleteLaboratory` function deletes a laboratory record from the repository based on the
     * provided laboratory ID.
     * 
     * @param idLaboratory The `idLaboratory` parameter is a unique identifier that is used to specify
     * which laboratory record should be deleted from the database.
     */
    public void deleteLaboratory(String idLaboratory){
        labRepository.deleteById(idLaboratory);
    }
    
}
