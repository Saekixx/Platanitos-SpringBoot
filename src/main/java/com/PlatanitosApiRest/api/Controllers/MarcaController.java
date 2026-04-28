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
        List<MarcaModel> marcas = marcaService.getAllMarcas();

        String msg = marcas.isEmpty() ? "No se encontraron marcas" : "Marcas obtenidas exitosamente";

        StandarResponse<List<MarcaModel>> response = new StandarResponse<List<MarcaModel>>(
            200,
            msg,
            marcas
        );

        return ResponseEntity.status(200).body(response);
    }

    @PostMapping()
    public ResponseEntity<StandarResponse<MarcaModel>> createMarca(@RequestBody MarcaModel data) {
        MarcaModel marca = marcaService.saveMarca(data);

        StandarResponse<MarcaModel> response = new StandarResponse<MarcaModel>(
            201,
            "Marca creada exitosamente",
            marca
        );

        return ResponseEntity.status(201).body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<StandarResponse<MarcaModel>> putMethodName(@PathVariable String id, @RequestBody MarcaModel data) {
        MarcaModel marca = marcaService.updateMarca(id, data);

        StandarResponse<MarcaModel> response = new StandarResponse<MarcaModel>(
            200,
            "Marca actualizada exitosamente",
            marca
        );

        return ResponseEntity.status(200).body(response);
    }
}
