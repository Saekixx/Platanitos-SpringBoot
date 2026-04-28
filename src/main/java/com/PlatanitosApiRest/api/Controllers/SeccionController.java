package com.PlatanitosApiRest.api.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlatanitosApiRest.api.Models.SeccionModel;
import com.PlatanitosApiRest.api.Services.SeccionService;
import com.PlatanitosApiRest.api.Util.StandarResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/secciones")
public class SeccionController {
    @Autowired private SeccionService seccionService;

    @GetMapping("")
    public ResponseEntity<StandarResponse<List<SeccionModel>>> allSeccion() {
        List<SeccionModel> secciones = seccionService.getAllSecciones();

        String msg = secciones.isEmpty() ? "No se encontraron secciones" : "Secciones obtenidas exitosamente";

        StandarResponse<List<SeccionModel>> response = new StandarResponse<List<SeccionModel>>(
            200,
            msg,
            secciones
        );

        return ResponseEntity.status(200).body(response);
    }
    

    @PostMapping()
    public ResponseEntity<StandarResponse<SeccionModel>> createSeccion(@RequestBody SeccionModel data) {
        SeccionModel seccion = seccionService.saveSeccion(data);

        StandarResponse<SeccionModel> response = new StandarResponse<SeccionModel>(
            201,
            "Sección creada exitosamente",
            seccion
        );
        
        
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandarResponse<SeccionModel>> editarSeccion(@PathVariable String id, @RequestBody SeccionModel data) {
        SeccionModel seccion = seccionService.updateSeccion(id, data);
        
        StandarResponse<SeccionModel> response = new StandarResponse<SeccionModel>(
            200,
            "Sección actualizada exitosamente",
            seccion
        );

        return ResponseEntity.status(200).body(response);
    }

}
