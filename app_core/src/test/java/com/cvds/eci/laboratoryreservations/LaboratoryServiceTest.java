package com.cvds.eci.laboratoryreservations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.cvds.eci.bookingreservations.app_core.controller.app_core.model.Laboratory;
import com.cvds.eci.bookingreservations.app_core.controller.app_core.service.LaboratoryService;


public class LaboratoryServiceTest {
	/**
	 * The test function verifies that adding a laboratory to a laboratory service updates the list of
	 * laboratories accordingly.
	 */
	
	 @Test
	    void shouldAddOneLaboratory() {
		 	LaboratoryService laboratoryService = new LaboratoryService();
	        Laboratory lab1 = new Laboratory("LABINFO", "Bloque B", 30, true);
	        laboratoryService.addLaboratory(lab1);

	        List<Laboratory> labs = laboratoryService.getLaboratories();

	        assertEquals(2, labs.size(), "La lista de laboratorios debe tener 2 elementos");
	        assertTrue(labs.contains(lab1), "La lista debe contener LABINFO");

	        assertEquals("LABINFO", labs.get(0).getName());
	    }

	@Test
	    void shouldAddSomeLaboratories() {
		 	LaboratoryService laboratoryService = new LaboratoryService();
	        Laboratory lab1 = new Laboratory("LABINFO", "Bloque B", 30, true);
			Laboratory lab2 = new Laboratory("LABRECO", "Bloque B", 10, false);
			Laboratory lab3 = new Laboratory("LABRESMAT", "Bloque D", 5, true);
			Laboratory lab4 = new Laboratory("LABINV", "Bloque E", 30, false);

	        laboratoryService.addLaboratory(lab1);
			laboratoryService.addLaboratory(lab2);
			laboratoryService.addLaboratory(lab3);
			laboratoryService.addLaboratory(lab4);

	        List<Laboratory> labs = laboratoryService.getLaboratories();

	        assertEquals(5, labs.size(), "La lista de laboratorios debe tener 2 elementos");
	        System.out.println(labs);

	        assertEquals("LABINFO", labs.get(0).getName());
	    }

}
