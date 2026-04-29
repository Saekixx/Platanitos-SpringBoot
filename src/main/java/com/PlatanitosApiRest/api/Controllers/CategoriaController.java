package com.PlatanitosApiRest.api.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlatanitosApiRest.api.DTO.Categoria.CategoriaRequestDTO;
import com.PlatanitosApiRest.api.Models.CategoriaModel;
import com.PlatanitosApiRest.api.Services.CategoriaService;
import com.PlatanitosApiRest.api.Util.StandarResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController 
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired private CategoriaService categoriaService;

    @GetMapping()
    public ResponseEntity<StandarResponse<List<CategoriaModel>>> allCategorias() {
        try {
            List<CategoriaModel> categorias = categoriaService.getAllCategorias();

            StandarResponse<List<CategoriaModel>> response = new StandarResponse<>(
                200,
                "Categorías obtenidas exitosamente",
                categorias
            );

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            StandarResponse<List<CategoriaModel>> errorResponse = new StandarResponse<>(
                500,
                "Error al obtener las categorías: " + e.getMessage(),
                null
            );
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping()
    public ResponseEntity<StandarResponse<CategoriaModel>> createCategoria(@RequestBody CategoriaRequestDTO data) {
        try {
            CategoriaModel categoria = categoriaService.saveCategoria(data);

            StandarResponse<CategoriaModel> response = new StandarResponse<>(
                201,
                "Categoría creada exitosamente",
                categoria
            );
            
            return ResponseEntity.status(201).body(response);
        } catch (RuntimeException e) {
            StandarResponse<CategoriaModel> errorResponse = new StandarResponse<>(
                400,
                e.getMessage(),
                null
            );
            return ResponseEntity.status(400).body(errorResponse);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<StandarResponse<CategoriaModel>> editarCategoria(@PathVariable String id, @RequestBody CategoriaRequestDTO data ) {
        try {
            CategoriaModel categoriaActualizada = categoriaService.updateCategoria(id, data);

            StandarResponse<CategoriaModel> response = new StandarResponse<>(
                200,
                "Categoría actualizada exitosamente",
                categoriaActualizada
            );

            return ResponseEntity.status(200).body(response);
        } catch (RuntimeException e) {
            StandarResponse<CategoriaModel> errorResponse = new StandarResponse<>(
                404,
                e.getMessage(),
                null
            );
            return ResponseEntity.status(404).body(errorResponse);
        }

    }
}
