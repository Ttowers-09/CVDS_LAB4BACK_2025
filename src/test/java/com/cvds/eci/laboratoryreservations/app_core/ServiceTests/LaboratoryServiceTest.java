package com.cvds.eci.laboratoryreservations.app_core.ServiceTests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cvds.eci.laboratoryreservations.app_core.model.Laboratory;
import com.cvds.eci.laboratoryreservations.app_core.repository.LaboratoryRepository;
import com.cvds.eci.laboratoryreservations.app_core.service.LaboratoryService;

/**
 * Pruebas unitarias para el servicio LaboratoryService.
 */
@ExtendWith(MockitoExtension.class)
public class LaboratoryServiceTest {
    
    @Mock
    private LaboratoryRepository labRepository;
    
    @InjectMocks
    private LaboratoryService laboratoryService;
    
    private Laboratory laboratory;
    
    @BeforeEach
    void setUp() {
        laboratory = new Laboratory("Lab A", "Edificio 1, Piso 2", 30, true);
    }
    
    /**
     * Verifica que se obtengan todos los laboratorios correctamente.
     */
    @Test
    void testGetLaboratories() {
        List<Laboratory> labs = new ArrayList<>();
        labs.add(laboratory);
        when(labRepository.findAll()).thenReturn(labs);
        
        List<Laboratory> result = laboratoryService.getLaboratories();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
    
    /**
     * Verifica que se agregue un laboratorio correctamente cuando no existe previamente.
     */
    @Test
    void testAddLaboratorySuccess() {
        when(labRepository.findByName(laboratory.getName())).thenReturn(null);
        when(labRepository.save(laboratory)).thenReturn(laboratory);
        
        Laboratory savedLab = laboratoryService.addLaboratory(laboratory);
        assertNotNull(savedLab);
        assertEquals("Lab A", savedLab.getName());
    }
    
    /**
     * Verifica que no se pueda agregar un laboratorio si ya existe.
     */
    @Test
    void testAddLaboratoryExists() {
        when(labRepository.findByName(laboratory.getName())).thenReturn(laboratory);
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            laboratoryService.addLaboratory(laboratory);
        });
        assertEquals("Laboratory Lab A exists already.", exception.getMessage());
    }
    
    /**
     * Verifica que un laboratorio pueda ser eliminado exitosamente.
     */
    @Test
    void testDeleteLaboratorySuccess() {
        when(labRepository.findById("1")).thenReturn(Optional.of(laboratory));
        doNothing().when(labRepository).deleteById("1");
        
        assertDoesNotThrow(() -> laboratoryService.deleteLaboratory("1"));
    }
    
    /**
     * Verifica que se lance una excepción al intentar eliminar un laboratorio inexistente.
     */
    @Test
    void testDeleteLaboratoryNotFound() {
        when(labRepository.findById("2")).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            laboratoryService.deleteLaboratory("2");
        });
        assertEquals("No Existe el laboratorio a eliminar", exception.getMessage());
    }
    
    /**
     * Verifica que un laboratorio pueda ser actualizado correctamente.
     */
    @Test
    void testUpdateLaboratorySuccess() {
        Laboratory updatedLab = new Laboratory("Lab B", "Edificio 2, Piso 3", 40, false);
        when(labRepository.findById("1")).thenReturn(Optional.of(laboratory));
        when(labRepository.save(any(Laboratory.class))).thenReturn(updatedLab);
        
        assertDoesNotThrow(() -> laboratoryService.updateLaboratory("1", updatedLab));
    }
    
    /**
     * Verifica que se lance una excepción al intentar actualizar un laboratorio inexistente.
     */
    @Test
    void testUpdateLaboratoryNotFound() {
        when(labRepository.findById("2")).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            laboratoryService.updateLaboratory("2", laboratory);
        });
        assertEquals("Laboratory with ID 2 not found", exception.getMessage());
    }
}
