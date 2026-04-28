package com.PlatanitosApiRest.api.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<ProductoModel> productos = productoService.getAllProductos();

        String msg = productos.isEmpty() ? "No se encontraron productos" : "Productos obtenidos exitosamente";

        StandarResponse<List<ProductoModel>> response = new StandarResponse<List<ProductoModel>>(
            200,
            msg,
            productos
        );

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandarResponse<ProductoModel>> getProducto(@PathVariable String id) {
        ProductoModel producto = productoService.getProducto(id);

        StandarResponse<ProductoModel> response = new StandarResponse<ProductoModel>(
            200,
            "Producto obtenido exitosamente",
            producto
        );

        return ResponseEntity.status(200).body(response);
    }

    @PostMapping()
    public ResponseEntity<StandarResponse<ProductoModel>> saveProducto(@RequestBody ProductoModel data) {
        ProductoModel newProducto = productoService.saveProducto(data);

        StandarResponse<ProductoModel> response = new StandarResponse<ProductoModel>(
            201,
            "Producto creado exitosamente",
            newProducto
        );

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandarResponse<ProductoModel>> updateProducto(@PathVariable String id, @RequestBody ProductoModel data) {
        ProductoModel updatedProducto = productoService.updateProducto(id, data);

        StandarResponse<ProductoModel> response = new StandarResponse<ProductoModel>(
            200,
            "Producto actualizado exitosamente",
            updatedProducto
        );

        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<StandarResponse<ProductoModel>> statusProducto(@PathVariable String id) {
        ProductoModel statusProduct = productoService.isActiveProducto(id);

        StandarResponse<ProductoModel> response = new StandarResponse<ProductoModel>(
            200,
            "Estado del producto actualizado exitosamente",
            statusProduct
        );

        return ResponseEntity.status(200).body(response);
    }
    
}
