package com.mcavlak.sosyobazaar.controllers;

import com.mcavlak.sosyobazaar.dtos.IndustryResponseDto;
import com.mcavlak.sosyobazaar.dtos.SaveIndustryDto;
import com.mcavlak.sosyobazaar.services.IndustryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/industry")
@CrossOrigin
public class IndustryController {

    private final IndustryService industryService;

    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @GetMapping
    public ResponseEntity<List<IndustryResponseDto>> findAll() {
        return ResponseEntity.ok(industryService.findAll());
    }

    @PostMapping
    public void save(@Valid @RequestBody SaveIndustryDto saveIndustryDto) {
        industryService.save(saveIndustryDto);
    }

    @DeleteMapping
    public void deleteById(@RequestParam(name = "id") Long id) {
        industryService.deleteById(id);
    }


}
