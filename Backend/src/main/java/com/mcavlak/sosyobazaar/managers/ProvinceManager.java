package com.mcavlak.sosyobazaar.managers;

import com.mcavlak.sosyobazaar.exception.EntityNotFoundException;
import com.mcavlak.sosyobazaar.models.entities.Province;
import com.mcavlak.sosyobazaar.repositories.ProvinceRepository;
import com.mcavlak.sosyobazaar.services.ProvinceService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProvinceManager implements ProvinceService {

    private final ProvinceRepository provinceRepository;

    public ProvinceManager(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public List<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Province findDataById(Long id) {
        return provinceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Konum bulunamamıştır."));
    }
}
