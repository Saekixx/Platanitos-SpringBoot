package com.PlatanitosApiRest.api.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlatanitosApiRest.api.DTO.Producto.ProductoRequestDTO;
import com.PlatanitosApiRest.api.Models.ProductoModel;
import com.PlatanitosApiRest.api.Services.ProductoService;
import com.PlatanitosApiRest.api.Util.StandarResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/productos")
public class ProductoContorller {
    @Autowired private ProductoService productoService;

    @GetMapping()
    public ResponseEntity<StandarResponse<List<ProductoModel>>> allProductos() {
        try {
            List<ProductoModel> productos = productoService.getAllProductos();

            StandarResponse<List<ProductoModel>> response = new StandarResponse<>(
                200,
                "Productos obtenidos exitosamente",
                productos
            );

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            StandarResponse<List<ProductoModel>> errorResponse = new StandarResponse<>(
                500,
                "Error al obtener los productos: " + e.getMessage(),
                null
            );
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandarResponse<ProductoModel>> getProducto(@PathVariable String id) {
        try {
            ProductoModel producto = productoService.getProducto(id);

            StandarResponse<ProductoModel> response = new StandarResponse<>(
                200,
                "Producto obtenido exitosamente",
                producto
            );

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            StandarResponse<ProductoModel> errorResponse = new StandarResponse<>(
                404,
                "Error al obtener el producto: " + e.getMessage(),
                null
            );
            return ResponseEntity.status(404).body(errorResponse);
        }
    }

    @PostMapping()
    public ResponseEntity<StandarResponse<ProductoModel>> saveProducto(@RequestBody ProductoRequestDTO data) {
        try {
            ProductoModel newProducto = productoService.saveProducto(data);

        StandarResponse<ProductoModel> response = new StandarResponse<ProductoModel>(
            201,
            "Producto creado exitosamente",
            newProducto
        );

            return ResponseEntity.status(201).body(response);
        } catch (RuntimeException e) {
            StandarResponse<ProductoModel> errorResponse = new StandarResponse<>(
                400,
                "Error al crear el producto: " + e.getMessage(),
                null
            );
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandarResponse<ProductoModel>> updateProducto(@PathVariable String id, @RequestBody ProductoRequestDTO data) {
        try {
            ProductoModel updatedProducto = productoService.updateProducto(id, data);

        StandarResponse<ProductoModel> response = new StandarResponse<ProductoModel>(
            200,
            "Producto actualizado exitosamente",
            updatedProducto
        );

            return ResponseEntity.status(200).body(response);
        } catch (RuntimeException e) {
            StandarResponse<ProductoModel> errorResponse = new StandarResponse<>(
                404,
                "Error al actualizar el producto: " + e.getMessage(),
                null
            );
            return ResponseEntity.status(404).body(errorResponse);
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<StandarResponse<String>> statusProducto(@PathVariable String id) {
        try {
            String statusMessage = productoService.isActiveProducto(id);

            StandarResponse<String> response = new StandarResponse<>(
                200,
                "Estado del producto actualizado exitosamente",
                statusMessage
            );

            return ResponseEntity.status(200).body(response);
        } catch (RuntimeException e) {
            StandarResponse<String> errorResponse = new StandarResponse<>(
                404,
                "Error al actualizar el estado del producto: " + e.getMessage(),
                null
            );
            return ResponseEntity.status(404).body(errorResponse);
        }
    }
}
