package com.mcavlak.sosyobazaar.services;

import com.mcavlak.sosyobazaar.models.entities.Province;

import java.util.List;

public interface ProvinceService {

    List<Province> findAll();

    Province findDataById(Long id);

}
