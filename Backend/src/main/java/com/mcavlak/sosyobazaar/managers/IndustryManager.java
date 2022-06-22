package com.mcavlak.sosyobazaar.managers;

import com.mcavlak.sosyobazaar.dtos.IndustryResponseDto;
import com.mcavlak.sosyobazaar.dtos.SaveIndustryDto;
import com.mcavlak.sosyobazaar.exception.EntityNotFoundException;
import com.mcavlak.sosyobazaar.mappers.IndustryMapper;
import com.mcavlak.sosyobazaar.models.entities.Industry;
import com.mcavlak.sosyobazaar.repositories.IndustryRepository;
import com.mcavlak.sosyobazaar.services.IndustryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IndustryManager implements IndustryService {

    private final IndustryRepository industryRepository;
    private final IndustryMapper industryMapper;

    public IndustryManager(IndustryRepository industryRepository,
                           IndustryMapper industryMapper) {
        this.industryRepository = industryRepository;
        this.industryMapper = industryMapper;
    }

    @Override
    public Industry findDataById(Long id) {
        return industryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sektör bulunamadı."));
    }

    @Override
    public void save(SaveIndustryDto saveIndustryDto) {
        industryRepository.save(Industry.create(saveIndustryDto.getName()));
    }

    @Override
    public void deleteById(Long id) {
        Industry industry = findDataById(id);
        industryRepository.delete(industry);
    }

    @Override
    public List<IndustryResponseDto> findAll() {
        return industryMapper.entityListToDtoList(industryRepository.findAll());
    }
}
