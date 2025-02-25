package com.cvds.eci.laboratoryreservations;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.tomcat.jni.Library;
import org.junit.jupiter.api.Test;

import com.cvds.eci.laboratoryreservations.app_core.model.Laboratory;
import com.cvds.eci.laboratoryreservations.app_core.service.LaboratoryService;
public class LaboratoryServiceTest {
    @Test
	void shouldAddLaboratory() {
    	Laboratory lab = new Laboratory("LABINFO","Bloque B", 30, true);
        LaboratoryService LaboratoryService = new LaboratoryService();
        LaboratoryService.addLaboratory(lab);
        System.out.println(LaboratoryService.getLaboratories());

    }

}
