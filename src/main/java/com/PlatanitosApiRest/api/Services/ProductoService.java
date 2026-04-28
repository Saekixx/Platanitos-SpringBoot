package com.PlatanitosApiRest.api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlatanitosApiRest.api.Repositories.IProductoRepository;

@Service
public class ProductoService {
    @Autowired private IProductoRepository productoRepository;
    @Autowired private CategoriaService categoriaService;
    @Autowired private MarcaService marcaService;
    @Autowired private TallaService tallaService;
}
