package com.mcavlak.sosyobazaar.services;

import com.mcavlak.sosyobazaar.dtos.IndustryResponseDto;
import com.mcavlak.sosyobazaar.dtos.SaveIndustryDto;
import com.mcavlak.sosyobazaar.models.entities.Industry;

import java.util.List;

public interface IndustryService {

    Industry findDataById(Long id);

    void save(SaveIndustryDto saveIndustryDto);

    void deleteById(Long id);

    List<IndustryResponseDto> findAll();
}
