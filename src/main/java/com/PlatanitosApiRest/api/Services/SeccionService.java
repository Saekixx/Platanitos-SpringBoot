package com.PlatanitosApiRest.api.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlatanitosApiRest.api.Exceptions.ResourceNotFoundException;
import com.PlatanitosApiRest.api.Models.SeccionModel;
import com.PlatanitosApiRest.api.Repositories.ISeccionRepository;

@Service
public class SeccionService {
    @Autowired private ISeccionRepository seccionRepository;

    public List<SeccionModel> getAllSecciones() {
        return seccionRepository.findAll();
    }

    public SeccionModel saveSeccion(SeccionModel seccion) {
        if (seccion.getNombre() == null || seccion.getNombre().isEmpty()) 
            throw new ResourceNotFoundException("El nombre de la sección es obligatorio");
        
        SeccionModel newSeccion = new SeccionModel();
        newSeccion.setNombre(seccion.getNombre());
        return seccionRepository.save(newSeccion);
    }

    public SeccionModel updateSeccion(String id, SeccionModel seccion){
        SeccionModel existingSeccion = seccionRepository.findById(Long.parseLong(id))
        .orElseThrow(() -> new ResourceNotFoundException("No se encontró la sección con ID: " + id));

        if (seccion.getNombre() == null || seccion.getNombre().isEmpty()) 
            throw new RuntimeException("El nombre de la sección no puede estar vacío");
        
        existingSeccion.setNombre(seccion.getNombre());

        return seccionRepository.save(existingSeccion);
    }
}
