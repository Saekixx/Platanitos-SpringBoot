package com.PlatanitosApiRest.api.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlatanitosApiRest.api.DTO.Producto.ProductoRequestDTO;
import com.PlatanitosApiRest.api.Models.ProductoModel;
import com.PlatanitosApiRest.api.Repositories.ICategoriaRepository;
import com.PlatanitosApiRest.api.Repositories.IMarcaRepository;
import com.PlatanitosApiRest.api.Repositories.IProductoRepository;
import com.PlatanitosApiRest.api.Repositories.ITallaRepository;

@Service
public class ProductoService {
    @Autowired private IProductoRepository productoRepository;
    @Autowired private ICategoriaRepository categoriaRepository;
    @Autowired private ITallaRepository tallaRepository;
    @Autowired private IMarcaRepository marcaRepository;

    public List<ProductoModel> getAllProductos() {
        return productoRepository.findAll();
    }

    public ProductoModel getProducto(String id){
        ProductoModel producto = productoRepository.findById(Long.parseLong(id))
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID: " + id));

        return producto;
    }

    public ProductoModel saveProducto(ProductoRequestDTO dto){
        ProductoModel producto = new ProductoModel();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        
        producto.setCategoria(categoriaRepository.findById(dto.getIdCategoria())
        .orElseThrow(() -> new RuntimeException("Categoría obligatoria no encontrada. ID: " + dto.getIdCategoria())));

        producto.setMarca(marcaRepository.findById(dto.getIdMarca())
        .orElseThrow(() -> new RuntimeException("Marca obligatoria no encontrada. ID: " + dto.getIdMarca())));

        producto.setTalla(tallaRepository.findById(dto.getIdTalla())
        .orElseThrow(() -> new RuntimeException("Talla obligatoria no encontrada. ID: " + dto.getIdTalla())));

        return productoRepository.save(producto);
    }

    public ProductoModel updateProducto(String id, ProductoRequestDTO dto) {
        ProductoModel producto = productoRepository.findById(Long.parseLong(id))
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID: " + id));

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());

        if(dto.getIdCategoria() != null) {
            producto.setCategoria(categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("No se encontró la categoría con ID: " + dto.getIdCategoria())));
        }

        if(dto.getIdMarca() != null) {
            producto.setMarca(marcaRepository.findById(dto.getIdMarca())
                .orElseThrow(() -> new RuntimeException("No se encontró la marca con ID: " + dto.getIdMarca())));
        }

        if(dto.getIdTalla() != null) {
            producto.setTalla(tallaRepository.findById(dto.getIdTalla())
                .orElseThrow(() -> new RuntimeException("No se encontró la talla con ID: " + dto.getIdTalla())));
        }

        return productoRepository.save(producto);
    }

    public String isActiveProducto(String id) {
        ProductoModel producto = productoRepository.findById(Long.parseLong(id))
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID: " + id));

        producto.setIsActive(!producto.getIsActive());
        productoRepository.save(producto);

        return producto.getIsActive() ? "Producto activado exitosamente" : "Producto desactivado exitosamente";
    }
}
