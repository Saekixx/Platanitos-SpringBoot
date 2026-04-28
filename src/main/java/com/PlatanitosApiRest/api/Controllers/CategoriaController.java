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
        List<CategoriaModel> categorias = categoriaService.getAllCategorias();

        String msg = categorias.isEmpty() ? "No se encontraron categorías" : "Categorías obtenidas exitosamente";

        StandarResponse<List<CategoriaModel>> response = new StandarResponse<List<CategoriaModel>>(
            200,
            msg,
            categorias
        );

        return ResponseEntity.status(200).body(response);
    }

    @PostMapping()
    public ResponseEntity<StandarResponse<CategoriaModel>> createCategoria(@RequestBody CategoriaRequestDTO data) {
        CategoriaModel categoria = categoriaService.saveCategoria(data);

        StandarResponse<CategoriaModel> response = new StandarResponse<>(
            201,
            "Categoría creada exitosamente",
            categoria
        );
        
        return ResponseEntity.status(201).body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<StandarResponse<CategoriaModel>> editarCategoria(@PathVariable String id, @RequestBody CategoriaRequestDTO data ) {
        CategoriaModel categoria = categoriaService.updateCategoria(id, data);
        
        StandarResponse<CategoriaModel> response = new StandarResponse<>(
            200,
            "Categoría actualizada exitosamente",
            categoria
        );
            
        return ResponseEntity.status(200).body(response);

    }
}
