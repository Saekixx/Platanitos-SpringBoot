package com.PlatanitosApiRest.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlatanitosApiRest.api.Models.ProductoModel;

public interface IProductoRepository extends JpaRepository<ProductoModel, Long>{

}
