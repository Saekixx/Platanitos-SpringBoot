package com.PlatanitosApiRest.api.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlatanitosApiRest.api.Models.MarcaModel;
import com.PlatanitosApiRest.api.Services.MarcaService;
import com.PlatanitosApiRest.api.Util.StandarResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/marcas")
public class MarcaController {  
    @Autowired private MarcaService marcaService;

    @GetMapping()
    public ResponseEntity<StandarResponse<List<MarcaModel>>> allMarcas(){
        try {
            List<MarcaModel> marcas = marcaService.getAllMarcas();

            StandarResponse<List<MarcaModel>> response = new StandarResponse<>(
                200,
                "Marcas obtenidas exitosamente",
                marcas
            );

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            StandarResponse<List<MarcaModel>> errorResponse = new StandarResponse<>(
                500,
                "Error al obtener las marcas: " + e.getMessage(),
                null
            );
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping()
    public ResponseEntity<StandarResponse<MarcaModel>> createMarca(@RequestBody MarcaModel data) {
        try {
            MarcaModel marca = marcaService.saveMarca(data);

            StandarResponse<MarcaModel> response = new StandarResponse<>(
                201,
                "Marca creada exitosamente",
                marca
            );

            return ResponseEntity.status(201).body(response);
        } catch (RuntimeException e) {
            StandarResponse<MarcaModel> errorResponse = new StandarResponse<>(
                400,
                e.getMessage(),
                null
            );
            return ResponseEntity.status(400).body(errorResponse);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<StandarResponse<MarcaModel>> putMethodName(@PathVariable String id, @RequestBody MarcaModel data) {
        try {
            MarcaModel marca = marcaService.updateMarca(id, data);

            StandarResponse<MarcaModel> response = new StandarResponse<>(
                200,
                "Marca actualizada exitosamente",
                marca
            );

            return ResponseEntity.status(200).body(response);
        } catch (RuntimeException e) {
            StandarResponse<MarcaModel> errorResponse = new StandarResponse<>(
                404,
                e.getMessage(),
                null
            );
            return ResponseEntity.status(404).body(errorResponse);
        }
    }
}

