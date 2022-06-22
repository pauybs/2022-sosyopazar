package com.mcavlak.sosyobazaar.controllers;

import com.mcavlak.sosyobazaar.annotations.Authenticated;
import com.mcavlak.sosyobazaar.annotations.PermitAllCustom;
import com.mcavlak.sosyobazaar.models.entities.Province;
import com.mcavlak.sosyobazaar.services.ProvinceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/province")
@CrossOrigin
@Authenticated
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping
    @PermitAllCustom
    public ResponseEntity<List<Province>> findAll() {
        return ResponseEntity.ok(provinceService.findAll());
    }

}
