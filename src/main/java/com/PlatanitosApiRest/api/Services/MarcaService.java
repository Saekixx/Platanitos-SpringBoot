package com.PlatanitosApiRest.api.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlatanitosApiRest.api.Models.MarcaModel;
import com.PlatanitosApiRest.api.Repositories.IMarcaRepository;

@Service
public class MarcaService {
    @Autowired private IMarcaRepository marcaRepository;

    public List<MarcaModel> getAllMarcas() {
        return marcaRepository.findAll();
    }

    public MarcaModel saveMarca(MarcaModel marca){
        if(marca.getNombre() == null || marca.getNombre().isEmpty()) 
            throw new RuntimeException("El nombre de la marca es obligatorio");

        if(marca.getDireccion() == null || marca.getDireccion().isEmpty()) 
            throw new RuntimeException("La dirección de la marca es obligatoria");

        if(marca.getRuc() == null || marca.getRuc().isEmpty())
            throw new RuntimeException("El RUC de la marca es obligatorio");

        MarcaModel newMarca = new MarcaModel();
        newMarca.setNombre(marca.getNombre());
        newMarca.setDireccion(marca.getDireccion());
        newMarca.setRuc(marca.getRuc());

        return marcaRepository.save(newMarca);
    }

    public MarcaModel updateMarca(String id, MarcaModel marca){
        MarcaModel existingMarca = marcaRepository.findById(Long.parseLong(id))
        .orElseThrow(() -> new RuntimeException("No se encontró la marca con ID: " + id));

        if (marca.getNombre() != null && !marca.getNombre().isEmpty()) 
            existingMarca.setNombre(marca.getNombre());
        
        if (marca.getDireccion() != null && !marca.getDireccion().isEmpty()) 
            existingMarca.setDireccion(marca.getDireccion());
        
        if (marca.getRuc() != null && !marca.getRuc().isEmpty()) 
            existingMarca.setRuc(marca.getRuc());

        return marcaRepository.save(existingMarca);
    }
}
