package com.PlatanitosApiRest.api.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlatanitosApiRest.api.DTO.Categoria.CategoriaRequestDTO;
import com.PlatanitosApiRest.api.Models.CategoriaModel;
import com.PlatanitosApiRest.api.Models.SeccionModel;
import com.PlatanitosApiRest.api.Repositories.ICategoriaRepository;
import com.PlatanitosApiRest.api.Repositories.ISeccionRepository;

@Service 
public class CategoriaService {
    @Autowired private ICategoriaRepository categoriaRepository;
    @Autowired private ISeccionRepository seccionRepository;

    public List<CategoriaModel> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public CategoriaModel saveCategoria(CategoriaRequestDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isEmpty()) 
            throw new RuntimeException("El nombre de la categoría es obligatorio");

        if (dto.getDescripcion() == null || dto.getDescripcion().isEmpty()) 
            throw new RuntimeException("La descripción de la categoría es obligatoria");

        CategoriaModel newCategoria = new CategoriaModel();
        newCategoria.setNombre(dto.getNombre());
        newCategoria.setDescripcion(dto.getDescripcion());

        if (dto.getIdSeccion() != null) {
            SeccionModel seccion = seccionRepository.findById(dto.getIdSeccion())
                .orElseThrow(() -> new RuntimeException("No se encontró la sección con ID: " + dto.getIdSeccion()));
            
            newCategoria.setSeccion(seccion);
        }

        return categoriaRepository.save(newCategoria);
    }

    public CategoriaModel updateCategoria(String id, CategoriaRequestDTO dto){
        CategoriaModel existingCategoria = categoriaRepository.findById(Long.parseLong(id))
            .orElseThrow(() -> new RuntimeException("No se encontró la categoría con ID: " + id));

        if (dto.getNombre() != null && !dto.getNombre().isEmpty()) 
            existingCategoria.setNombre(dto.getNombre());

        if (dto.getDescripcion() != null && !dto.getDescripcion().isEmpty())
            existingCategoria.setDescripcion(dto.getDescripcion());

        if (dto.getIdSeccion() != null) {
            SeccionModel nuevaSeccion = seccionRepository.findById(dto.getIdSeccion())
                .orElseThrow(() -> new RuntimeException("No se encontró la sección con ID: " + dto.getIdSeccion()));
        
            existingCategoria.setSeccion(nuevaSeccion);
        }

        return categoriaRepository.save(existingCategoria);
    }
}
