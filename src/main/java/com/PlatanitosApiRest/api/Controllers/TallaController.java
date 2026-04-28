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
        List<TallaModel> tallas = tallaService.getAllTallas();

        String msg = tallas.isEmpty() ? "No se encontraron tallas" : "Tallas obtenidas exitosamente";

        StandarResponse<List<TallaModel>> response = new StandarResponse<List<TallaModel>>(
            200,
            msg,
            tallas
        );

        return ResponseEntity.status(200).body(response);
    }
    
}
