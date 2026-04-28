package com.PlatanitosApiRest.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlatanitosApiRest.api.Models.CategoriaModel;

public interface ICategoriaRepository extends JpaRepository<CategoriaModel, Long>{

}
