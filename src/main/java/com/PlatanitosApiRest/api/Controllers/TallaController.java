package com.PlatanitosApiRest.api.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlatanitosApiRest.api.Models.TallaModel;
import com.PlatanitosApiRest.api.Services.TallaService;
import com.PlatanitosApiRest.api.Util.StandarResponse;

import org.springframework.web.bind.annotation.GetMapping;


@RestController  
@RequestMapping("/api/tallas")
public class TallaController {
    @Autowired private TallaService tallaService;

    @GetMapping()
    public ResponseEntity<StandarResponse<List<TallaModel>>> allTallas () {
        try {
            List<TallaModel> tallas = tallaService.getAllTallas();

            StandarResponse<List<TallaModel>> response = new StandarResponse<>(
                200,
                "Tallas obtenidas exitosamente",
                tallas
            );

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            StandarResponse<List<TallaModel>> errorResponse = new StandarResponse<>(
                500,
                "Error al obtener las tallas: " + e.getMessage(),
                null
            );
            return ResponseEntity.status(500).body(errorResponse);
        }
    } 
}
