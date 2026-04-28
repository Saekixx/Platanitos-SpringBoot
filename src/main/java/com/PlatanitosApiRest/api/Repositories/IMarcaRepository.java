package com.PlatanitosApiRest.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlatanitosApiRest.api.Models.MarcaModel;

public interface IMarcaRepository extends JpaRepository<MarcaModel, Long>{

}
