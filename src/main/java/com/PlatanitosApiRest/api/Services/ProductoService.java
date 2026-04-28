package com.PlatanitosApiRest.api.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlatanitosApiRest.api.Models.ProductoModel;
import com.PlatanitosApiRest.api.Repositories.IProductoRepository;

@Service
public class ProductoService {
    @Autowired private IProductoRepository productoRepository;

    public List<ProductoModel> getAllProductos() {
        return productoRepository.findAll();
    }

    public ProductoModel getProducto(String id){
        ProductoModel marca = productoRepository.findById(Long.parseLong(id))
        .orElseThrow(() -> new RuntimeException("No se encontró la marca con ID: " + id));

        return marca;
    }

    public ProductoModel saveProducto(ProductoModel data) {
        if(data.getNombre() == null || data.getNombre().isEmpty()) 
            throw new RuntimeException("El nombre del producto es obligatorio");

        if(data.getDescripcion() == null || data.getDescripcion().isEmpty())
            throw new RuntimeException("La descripción del producto es obligatoria");

        if(data.getPrecio() == null || data.getPrecio() <= 0)
            throw new RuntimeException("El precio del producto es obligatorio y debe ser mayor a 0");

        if(data.getIdCategoria() == null)
            throw new RuntimeException("El ID de la categoría del producto es obligatoria");

        if(data.getIdMarca() == null)
            throw new RuntimeException("El ID de la marca del producto es obligatoria");

        ProductoModel newProducto = new ProductoModel();
        newProducto.setNombre(data.getNombre());
        newProducto.setDescripcion(data.getDescripcion());
        newProducto.setPrecio(data.getPrecio());
        newProducto.setStock(data.getStock() != null ? data.getStock() : 0);
        newProducto.setIsFavorite(data.getIsFavorite() != null ? data.getIsFavorite() : false);
        newProducto.setIsActive(data.getIsActive() != null ? data.getIsActive() : true);
        newProducto.setImagenUrl(data.getImagenUrl() != null ? data.getImagenUrl() : null);
        newProducto.setIdCategoria(data.getIdCategoria());
        newProducto.setIdMarca(data.getIdMarca());
        newProducto.setIdTalla(data.getIdTalla() != null ? data.getIdTalla() : null);

        return productoRepository.save(newProducto);
    }

    public ProductoModel updateProducto(String id, ProductoModel data) {
        ProductoModel existingProducto = productoRepository.findById(Long.parseLong(id))
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID: " + id));

        if (data.getNombre() != null && !data.getNombre().isEmpty()) 
            existingProducto.setNombre(data.getNombre());
        
        if (data.getDescripcion() != null && !data.getDescripcion().isEmpty()) 
            existingProducto.setDescripcion(data.getDescripcion());
        
        if (data.getPrecio() != null && data.getPrecio() > 0) 
            existingProducto.setPrecio(data.getPrecio());
        
        if (data.getStock() != null) 
            existingProducto.setStock(data.getStock());
        
        if (data.getIsFavorite() != null) 
            existingProducto.setIsFavorite(data.getIsFavorite());
        
        if (data.getIsActive() != null) 
            existingProducto.setIsActive(data.getIsActive());
        
        if (data.getImagenUrl() != null && !data.getImagenUrl().isEmpty()) 
            existingProducto.setImagenUrl(data.getImagenUrl());
        
        if (data.getIdCategoria() != null) 
            existingProducto.setIdCategoria(data.getIdCategoria());
        
        if (data.getIdMarca() != null) 
            existingProducto.setIdMarca(data.getIdMarca());
        
        if (data.getIdTalla() != null) 
            existingProducto.setIdTalla(data.getIdTalla());

        return productoRepository.save(existingProducto);
    }

    public ProductoModel isActiveProducto(String id) {
        ProductoModel existingProducto = productoRepository.findById(Long.parseLong(id))
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID: " + id));

        if(existingProducto.getIsActive() == null || existingProducto.getIsActive()) {
            existingProducto.setIsActive(false);
        } else {
            existingProducto.setIsActive(true);
        }

        return productoRepository.save(existingProducto);
    }
}
