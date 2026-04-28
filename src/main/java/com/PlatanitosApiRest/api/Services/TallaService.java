package com.PlatanitosApiRest.api.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlatanitosApiRest.api.Models.TallaModel;
import com.PlatanitosApiRest.api.Repositories.ITallaRepository;

@Service
public class TallaService {
    @Autowired private ITallaRepository tallaRepository;

    public List<TallaModel> getAllTallas() {
        return tallaRepository.findAll();
    }
}
