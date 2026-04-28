package com.PlatanitosApiRest.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlatanitosApiRest.api.Models.TallaModel;

public interface ITallaRepository extends JpaRepository<TallaModel, Long>{

}
